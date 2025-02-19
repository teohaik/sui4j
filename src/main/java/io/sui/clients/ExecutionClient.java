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


import io.sui.models.transactions.ExecuteTransactionRequestType;
import io.sui.models.transactions.TransactionBlockResponse;
import io.sui.models.transactions.TransactionBlockResponseOptions;
import io.sui.models.transactions.TransactionEffects;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * The interface Execution client.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface ExecutionClient {

  /**
   * Dry run transaction completable future.
   *
   * @param txBytes the tx bytes
   * @return the completable future
   */
  CompletableFuture<TransactionEffects> dryRunTransaction(String txBytes);

  /**
   * Execute transaction completable future.
   *
   * @param txBytes the tx bytes
   * @param signatures the signatures
   * @param transactionBlockResponseOptions the transaction block response options
   * @param requestType the request type
   * @return the completable future
   */
  CompletableFuture<TransactionBlockResponse> executeTransaction(
      String txBytes,
      List<String> signatures,
      TransactionBlockResponseOptions transactionBlockResponseOptions,
      ExecuteTransactionRequestType requestType);
}
