package org.thoughtcrimedual.securesms.jobs

import org.signal.core.util.logging.Log
import org.thoughtcrimedual.securesms.database.SignalDatabase
import org.thoughtcrimedual.securesms.dependencies.AppDependencies
import org.thoughtcrimedual.securesms.jobmanager.Job
import org.thoughtcrimedual.securesms.jobmanager.impl.DataRestoreConstraint
import org.thoughtcrimedual.securesms.transport.RetryLaterException
import java.lang.Exception
import kotlin.time.Duration.Companion.seconds

class RebuildMessageSearchIndexJob private constructor(params: Parameters) : BaseJob(params) {

  companion object {
    private val TAG = Log.tag(RebuildMessageSearchIndexJob::class.java)

    const val KEY = "RebuildMessageSearchIndexJob"

    fun enqueue() {
      AppDependencies.jobManager.add(RebuildMessageSearchIndexJob())
    }
  }

  private constructor() : this(
    Parameters.Builder()
      .setQueue("RebuildMessageSearchIndex")
      .addConstraint(DataRestoreConstraint.KEY)
      .setMaxAttempts(3)
      .build()
  )

  override fun serialize(): ByteArray? = null

  override fun getFactoryKey(): String = KEY

  override fun onFailure() = Unit

  override fun onRun() {
    val success = SignalDatabase.messageSearch.rebuildIndex()

    if (!success) {
      Log.w(TAG, "Failed to rebuild search index. Resetting tables. That will enqueue another copy of this job as a side-effect.")
      SignalDatabase.messageSearch.fullyResetTables()
    }
  }

  override fun getNextRunAttemptBackoff(pastAttemptCount: Int, exception: Exception): Long {
    return 10.seconds.inWholeMilliseconds
  }

  override fun onShouldRetry(e: Exception): Boolean = e is RetryLaterException

  class Factory : Job.Factory<RebuildMessageSearchIndexJob> {
    override fun create(parameters: Parameters, serializedData: ByteArray?): RebuildMessageSearchIndexJob {
      return RebuildMessageSearchIndexJob(parameters)
    }
  }
}
