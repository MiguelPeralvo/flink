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

package org.apache.flink.streaming.api.streamvertex;

import org.apache.flink.streaming.api.StreamConfig;
import org.apache.flink.streaming.api.streamrecord.StreamRecordSerializer;
import org.apache.flink.streaming.io.CoReaderIterator;
import org.apache.flink.streaming.io.IndexedReaderIterator;
import org.apache.flink.util.Collector;
import org.apache.flink.util.MutableObjectIterator;

public interface StreamTaskContext<OUT> {

	StreamConfig getConfig();

	ClassLoader getUserCodeClassLoader();

	<X> MutableObjectIterator<X> getInput(int index);
	
	<X> IndexedReaderIterator<X> getIndexedInput(int index);

	<X> StreamRecordSerializer<X> getInputSerializer(int index);

	Collector<OUT> getOutputCollector();

	<X, Y> CoReaderIterator<X, Y> getCoReader();
}
