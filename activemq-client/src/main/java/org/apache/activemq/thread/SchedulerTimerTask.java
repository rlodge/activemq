/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.thread;

import org.slf4j.Logger;

import java.util.TimerTask;

/**
 * A TimeTask for a Runnable object
 *
 */
public class SchedulerTimerTask extends TimerTask {
    private final Runnable task;
    private final Logger log;

    public SchedulerTimerTask(Runnable task) {
        this.task = task;
        this.log = null;
    }

    public SchedulerTimerTask(Runnable task, Logger log) {
        this.task = task;
        this.log = log;
    }

    public void run() {
        try {
            this.task.run();
        } catch (Throwable t) {
            try {
                if (log != null) {
                    log.error("Exception in scheduled timer task", t);
                } else {
                    t.printStackTrace();
                }
            } catch (Throwable t2) {
                //not much to do...
            }
        }
    }
}