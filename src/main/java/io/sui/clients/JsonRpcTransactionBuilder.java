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


import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import io.sui.jsonrpc.JsonRpc20Request;
import io.sui.jsonrpc.JsonRpcClientProvider;
import io.sui.models.transactions.RPCTransactionRequestParams;
import io.sui.models.transactions.TransactionBytes;
import io.sui.models.transactions.TypeTag;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * The type Json rpc transaction builder.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class JsonRpcTransactionBuilder implements TransactionBuilder {

  private final JsonRpcClientProvider jsonRpcClientProvider;

  /**
   * Instantiates a new Json rpc transaction builder.
   *
   * @param jsonRpcClientProvider the json rpc client provider
   */
  public JsonRpcTransactionBuilder(JsonRpcClientProvider jsonRpcClientProvider) {
    this.jsonRpcClientProvider = jsonRpcClientProvider;
  }

  @Override
  public CompletableFuture<TransactionBytes> splitCoin(
      String signer, String coin, List<Long> splitAmounts, String gas, long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_splitCoin", Lists.newArrayList(signer, coin, splitAmounts, gas, gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_splitCoin", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> splitCoinEqual(
      String signer, String coin, long splitCount, String gas, long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_splitCoinEqual", Lists.newArrayList(signer, coin, splitCount, gas, gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_splitCoinEqual", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> mergeCoins(
      String signer, String primaryCoin, String toMergeCoin, String gas, long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_mergeCoins", Lists.newArrayList(signer, primaryCoin, toMergeCoin, gas, gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_mergeCoins", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> pay(
      String signer,
      List<String> inputCoins,
      List<String> recipients,
      List<Long> amounts,
      String gas,
      Long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "unsafe_pay",
            Lists.newArrayList(
                signer,
                inputCoins,
                recipients,
                amounts.stream().map(String::valueOf).collect(Collectors.toList()),
                gas,
                gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/unsafe_pay", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> paySui(
      String signer,
      List<String> inputCoins,
      List<String> recipients,
      List<Long> amounts,
      Long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "unsafe_paySui",
            Lists.newArrayList(
                signer,
                inputCoins,
                recipients,
                amounts.stream().map(String::valueOf).collect(Collectors.toList()),
                gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/unsafe_paySui", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> payAllSui(
      String signer, List<String> inputCoins, String recipient, Long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "unsafe_payAllSui", Lists.newArrayList(signer, inputCoins, recipient, gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/unsafe_payAllSui", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> transferSui(
      String signer, String coin, Long gasBudget, String recipient, Long amount) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "unsafe_transferSui", Lists.newArrayList(signer, coin, gasBudget, recipient, amount));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/unsafe_transferSui", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> transferObject(
      String signer, String suiObject, String recipient, String gas, Long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "unsafe_transferObject",
            Lists.newArrayList(signer, suiObject, gas, gasBudget, recipient));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/unsafe_transferObject", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> batchTransaction(
      String signer,
      List<RPCTransactionRequestParams> batchTransactionParams,
      String gas,
      long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_batchTransaction",
            Lists.newArrayList(signer, batchTransactionParams, gas, gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_batchTransaction", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> moveCall(
      String signer,
      String packageObjectId,
      String module,
      String function,
      List<TypeTag> typeArguments,
      List<?> arguments,
      String gas,
      long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "sui_moveCall",
            Lists.newArrayList(
                signer,
                packageObjectId,
                module,
                function,
                typeArguments,
                arguments,
                gas,
                gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/sui_moveCall", request, new TypeToken<TransactionBytes>() {}.getType());
  }

  @Override
  public CompletableFuture<TransactionBytes> publish(
      String signer,
      List<String> compiledModules,
      List<String> depIds,
      String gas,
      Long gasBudget) {
    final JsonRpc20Request request =
        this.jsonRpcClientProvider.createJsonRpc20Request(
            "unsafe_publish", Lists.newArrayList(signer, compiledModules, depIds, gas, gasBudget));
    return this.jsonRpcClientProvider.callAndUnwrapResponse(
        "/unsafe_publish", request, new TypeToken<TransactionBytes>() {}.getType());
  }
}
