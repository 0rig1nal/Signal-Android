package org.thoughtcrimedual.securesms.stories.settings.connections

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.thoughtcrimedual.securesms.R
import org.thoughtcrimedual.securesms.components.ViewBinderDelegate
import org.thoughtcrimedual.securesms.components.WrapperDialogFragment
import org.thoughtcrimedual.securesms.contacts.LetterHeaderDecoration
import org.thoughtcrimedual.securesms.contacts.paged.ContactSearchAdapter
import org.thoughtcrimedual.securesms.contacts.paged.ContactSearchConfiguration
import org.thoughtcrimedual.securesms.contacts.paged.ContactSearchMediator
import org.thoughtcrimedual.securesms.databinding.ViewAllSignalConnectionsFragmentBinding
import org.thoughtcrimedual.securesms.groups.SelectionLimits

class ViewAllSignalConnectionsFragment : Fragment(R.layout.view_all_signal_connections_fragment) {

  private val binding by ViewBinderDelegate(ViewAllSignalConnectionsFragmentBinding::bind)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    binding.recycler.addItemDecoration(LetterHeaderDecoration(requireContext()) { false })
    binding.toolbar.setNavigationOnClickListener {
      requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    val mediator = ContactSearchMediator(
      fragment = this,
      selectionLimits = SelectionLimits(0, 0),
      displayOptions = ContactSearchAdapter.DisplayOptions(
        displayCheckBox = false,
        displaySecondaryInformation = ContactSearchAdapter.DisplaySecondaryInformation.NEVER
      ),
      mapStateToConfiguration = { getConfiguration() },
      performSafetyNumberChecks = false
    )

    binding.recycler.adapter = mediator.adapter
  }

  private fun getConfiguration(): ContactSearchConfiguration {
    return ContactSearchConfiguration.build {
      addSection(
        ContactSearchConfiguration.Section.Individuals(
          includeHeader = false,
          includeSelf = false,
          includeLetterHeaders = true,
          transportType = ContactSearchConfiguration.TransportType.PUSH
        )
      )
    }
  }

  class Dialog : WrapperDialogFragment() {
    override fun getWrappedFragment(): Fragment {
      return ViewAllSignalConnectionsFragment()
    }

    companion object {
      fun show(fragmentManager: FragmentManager) {
        Dialog().show(fragmentManager, null)
      }
    }
  }
}
