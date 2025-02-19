/*
 * Copyright 2022-2023 281165273grape@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.sui.clients;


import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.sui.models.SuiApiException;
import io.sui.models.events.EventFilter;
import io.sui.models.events.SuiEvent;

/**
 * The interface Event client.
 *
 * @author grapebaba
 * @since 2022.12
 */
public interface EventClient {

  /**
   * Subscribe event disposable.
   *
   * @param eventFilter the event filter
   * @param onNext the on next
   * @param onError the on error
   * @return the disposable
   */
  Disposable subscribeEvent(
      EventFilter eventFilter, Consumer<SuiEvent> onNext, Consumer<SuiApiException> onError);
}
