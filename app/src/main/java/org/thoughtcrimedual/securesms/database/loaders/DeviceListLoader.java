package org.thoughtcrimedual.securesms.database.loaders;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.annimon.stream.Stream;

import org.signal.core.util.logging.Log;
import org.thoughtcrimedual.securesms.devicelist.Device;
import org.thoughtcrimedual.securesms.keyvalue.SignalStore;
import org.thoughtcrimedual.securesms.registration.secondary.DeviceNameCipher;
import org.thoughtcrimedual.securesms.util.AsyncLoader;
import org.signal.core.util.Base64;
import org.whispersystems.signalservice.api.SignalServiceAccountManager;
import org.whispersystems.signalservice.api.messages.multidevice.DeviceInfo;
import org.whispersystems.signalservice.api.push.SignalServiceAddress;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.thoughtcrimedual.securesms.devicelist.protos.DeviceName;

public class DeviceListLoader extends AsyncLoader<List<Device>> {

  private static final String TAG = Log.tag(DeviceListLoader.class);

  private final SignalServiceAccountManager accountManager;

  public DeviceListLoader(Context context, SignalServiceAccountManager accountManager) {
    super(context);
    this.accountManager = accountManager;
  }

  @Override
  public List<Device> loadInBackground() {
    try {
      List<Device> devices = Stream.of(accountManager.getDevices())
                                   .filter(d -> d.getId() != SignalServiceAddress.DEFAULT_DEVICE_ID)
                                   .map(this::mapToDevice)
                                   .toList();

      Collections.sort(devices, new DeviceComparator());

      return devices;
    } catch (IOException e) {
      Log.w(TAG, e);
      return null;
    }
  }

  private Device mapToDevice(@NonNull DeviceInfo deviceInfo) {
    try {
      if (TextUtils.isEmpty(deviceInfo.getName()) || deviceInfo.getName().length() < 4) {
        throw new IOException("Invalid DeviceInfo name.");
      }

      DeviceName deviceName = DeviceName.ADAPTER.decode(Base64.decode(deviceInfo.getName()));

      if (deviceName.ciphertext == null || deviceName.ephemeralPublic == null || deviceName.syntheticIv == null) {
        throw new IOException("Got a DeviceName that wasn't properly populated.");
      }

      byte[] plaintext = DeviceNameCipher.decryptDeviceName(deviceName, SignalStore.account().getAciIdentityKey());
      if (plaintext == null) {
        throw new IOException("Failed to decrypt device name.");
      }

      return new Device(deviceInfo.getId(), new String(plaintext), deviceInfo.getCreated(), deviceInfo.getLastSeen());
    } catch (IOException e) {
      Log.w(TAG, "Failed while reading the protobuf.", e);
    }

    return new Device(deviceInfo.getId(), deviceInfo.getName(), deviceInfo.getCreated(), deviceInfo.getLastSeen());
  }

  private static class DeviceComparator implements Comparator<Device> {

    @Override
    public int compare(Device lhs, Device rhs) {
      if      (lhs.getCreated() < rhs.getCreated())  return -1;
      else if (lhs.getCreated() != rhs.getCreated()) return 1;
      else                                           return 0;
    }
  }
}
