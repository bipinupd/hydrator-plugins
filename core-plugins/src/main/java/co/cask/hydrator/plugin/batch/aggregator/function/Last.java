/*
 * Copyright © 2016 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package co.cask.hydrator.plugin.batch.aggregator.function;

import co.cask.cdap.api.data.format.StructuredRecord;
import co.cask.cdap.api.data.schema.Schema;

/**
 * Return the last element in the group.
 *
 * @param <T> type of aggregate value
 */
public class Last<T> implements AggregateFunction<T> {
  private final String fieldName;
  private final Schema fieldSchema;
  private T last;

  public Last(String fieldName, Schema fieldSchema) {
    this.fieldName = fieldName;
    this.fieldSchema = fieldSchema;
  }

  @Override
  public void beginAggregate() {
    last = null;
  }

  @Override
  public void update(StructuredRecord record) {
    last = record.get(fieldName);
  }

  @Override
  public T finishAggregate() {
    return last;
  }

  @Override
  public Schema getOutputSchema() {
    return fieldSchema;
  }
}