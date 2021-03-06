/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.yarn

import java.util.Date

import akka.actor.ActorRef
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.yarn.api.records.FinalApplicationStatus

object Messages {
  case class YarnMessage(message: String, date: Date = new Date())
  case class ApplicationMasterStatus(numTaskManagers: Int, numSlots: Int)
  case object RegisterClient

  case class StopYarnSession(status: FinalApplicationStatus)
  case object JobManagerStopped
  case class StartYarnSession(configuration: Configuration, actorSystemPort: Int)

  case class JobManagerActorRef(jobManager: ActorRef)

  case object PollContainerCompletion
  case object PollYarnClusterStatus // see org.apache.flink.runtime.yarn.FlinkYarnClusterStatus for
                                    // the response
  case object CheckForUserCommand

  // Client-local messages
  case class LocalRegisterClient(jobManagerAddress: String)
  case object LocalGetYarnMessage // request new message
  case object LocalGetYarnClusterStatus // request the latest cluster status

  def getLocalGetYarnMessage(): AnyRef = {
    LocalGetYarnMessage
  }
}
