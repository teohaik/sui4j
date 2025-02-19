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


import io.sui.models.events.EventId;
import io.sui.models.events.EventQuery;
import io.sui.models.events.PaginatedEvents;
import io.sui.models.objects.Balance;
import io.sui.models.objects.CheckpointContents;
import io.sui.models.objects.CheckpointSummary;
import io.sui.models.objects.CoinMetadata;
import io.sui.models.objects.CommitteeInfoResponse;
import io.sui.models.objects.MoveFunctionArgType;
import io.sui.models.objects.MoveNormalizedFunction;
import io.sui.models.objects.MoveNormalizedModule;
import io.sui.models.objects.MoveNormalizedStruct;
import io.sui.models.objects.ObjectDataOptions;
import io.sui.models.objects.ObjectResponse;
import io.sui.models.objects.ObjectResponseQuery;
import io.sui.models.objects.PaginatedCoins;
import io.sui.models.objects.PaginatedObjectsResponse;
import io.sui.models.objects.SuiObjectRef;
import io.sui.models.objects.SuiObjectResponse;
import io.sui.models.objects.SuiSystemState;
import io.sui.models.objects.ValidatorMetadata;
import io.sui.models.transactions.PaginatedTransactionResponse;
import io.sui.models.transactions.TransactionBlockResponse;
import io.sui.models.transactions.TransactionBlockResponseOptions;
import io.sui.models.transactions.TransactionBlockResponseQuery;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * The interface Sui client.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface QueryClient {

  /** The constant DEFAULT_COIN_TYPE. */
  String DEFAULT_COIN_TYPE = "0x2::sui::SUI";

  /**
   * Gets object.
   *
   * @param id the id
   * @param objectDataOptions the object data options
   * @return the object
   */
  CompletableFuture<SuiObjectResponse> getObject(String id, ObjectDataOptions objectDataOptions);

  /**
   * Gets objects owned by address.
   *
   * @param address the address
   * @param query the query
   * @param cursor the cursor
   * @param limit the limit
   * @return the objects owned by address
   */
  CompletableFuture<PaginatedObjectsResponse> getObjectsOwnedByAddress(
      String address, ObjectResponseQuery query, String cursor, Integer limit);

  /**
   * Gets total transaction number.
   *
   * @return the total transaction number
   */
  CompletableFuture<Long> getTotalTransactionBlocks();

  /**
   * Gets transaction.
   *
   * @param digest the digest
   * @param options the options
   * @return the transaction
   */
  CompletableFuture<TransactionBlockResponse> getTransactionBlock(
      String digest, TransactionBlockResponseOptions options);

  /**
   * Multi get transaction blocks completable future.
   *
   * @param digests the digests
   * @param options the options
   * @return the completable future
   */
  CompletableFuture<List<TransactionBlockResponse>> multiGetTransactionBlocks(
      List<String> digests, TransactionBlockResponseOptions options);

  /**
   * Multi get objects completable future.
   *
   * @param objectIds the object ids
   * @param options the options
   * @return the completable future
   */
  CompletableFuture<List<SuiObjectResponse>> multiGetObjects(
      List<String> objectIds, ObjectDataOptions options);

  /**
   * Gets sui system state.
   *
   * @return the sui system state
   */
  CompletableFuture<SuiSystemState> getSuiSystemState();

  /**
   * Return all validators available for stake delegation.
   *
   * @return all validators available for stake delegation.
   */
  CompletableFuture<List<ValidatorMetadata>> getValidators();

  /**
   * Gets events.
   *
   * @param query the query
   * @param cursor the cursor
   * @param limit the limit
   * @param isDescOrder the is desc order
   * @return the events
   */
  CompletableFuture<PaginatedEvents> getEvents(
      EventQuery query, EventId cursor, int limit, boolean isDescOrder);

  /**
   * Gets normalized move modules by package.
   *
   * @param packageId the package id
   * @return the normalized move modules by package
   */
  CompletableFuture<Map<String, MoveNormalizedModule>> getNormalizedMoveModulesByPackage(
      String packageId);

  /**
   * Gets committee info.
   *
   * @param epoch the epoch
   * @return the committee info
   */
  CompletableFuture<CommitteeInfoResponse> getCommitteeInfo(Long epoch);

  /**
   * Gets move function arg types.
   *
   * @param suiPackage the sui package
   * @param module the module
   * @param function the function
   * @return the move function arg types
   */
  CompletableFuture<List<MoveFunctionArgType>> getMoveFunctionArgTypes(
      String suiPackage, String module, String function);

  /**
   * Gets normalized move function.
   *
   * @param suiPackage the sui package
   * @param module the module
   * @param function the function
   * @return the normalized move function
   */
  CompletableFuture<MoveNormalizedFunction> getNormalizedMoveFunction(
      String suiPackage, String module, String function);

  /**
   * Gets normalized move module.
   *
   * @param suiPackage the sui package
   * @param module the module
   * @return the normalized move module
   */
  CompletableFuture<MoveNormalizedModule> getNormalizedMoveModule(String suiPackage, String module);

  /**
   * Gets normalized move struct.
   *
   * @param suiPackage the sui package
   * @param module the module
   * @param struct the struct
   * @return the normalized move struct
   */
  CompletableFuture<MoveNormalizedStruct> getNormalizedMoveStruct(
      String suiPackage, String module, String struct);

  /**
   * Try get past object completable future.
   *
   * @param objectId the object id
   * @param version the version
   * @return the completable future
   */
  CompletableFuture<ObjectResponse> tryGetPastObject(String objectId, long version);

  /**
   * Gets transactions.
   *
   * @param query the query
   * @param cursor the cursor
   * @param limit the limit
   * @param isDescOrder the is desc order
   * @return the transactions
   */
  CompletableFuture<PaginatedTransactionResponse> queryTransactionBlocks(
      TransactionBlockResponseQuery query, String cursor, Integer limit, boolean isDescOrder);

  /**
   * Query objects completable future.
   *
   * @param query the query
   * @param cursor the cursor
   * @param limit the limit
   * @return the completable future
   */
  CompletableFuture<PaginatedObjectsResponse> queryObjects(
      ObjectResponseQuery query, String cursor, Integer limit);

  /**
   * Gets coin metadata.
   *
   * @param coinType the coin type
   * @return the coin metadata
   */
  CompletableFuture<CoinMetadata> getCoinMetadata(String coinType);

  /**
   * Gets reference gas price.
   *
   * @return the reference gas price
   */
  CompletableFuture<Long> getReferenceGasPrice();

  /**
   * Gets object ref.
   *
   * @param id the id
   * @param objectDataOptions the object data options
   * @return the object ref
   */
  CompletableFuture<SuiObjectRef> getObjectRef(String id, ObjectDataOptions objectDataOptions);

  /**
   * Return the total coin balance for all coin type, owned by the address owner.
   *
   * @param address the owner address
   * @return the total coin balance for all coin type, owned by the address owner.
   */
  CompletableFuture<List<Balance>> getAllBalances(String address);

  /**
   * Return all Coin objects owned by an address.
   *
   * @param address the owner address
   * @param cursor the cursor
   * @param limit the limit
   * @return all Coin objects owned by the address.
   */
  CompletableFuture<PaginatedCoins> getAllCoins(String address, String cursor, Integer limit);

  /**
   * Return all Coin with coin_type objects owned by an address.
   *
   * @param address the owner address
   * @param coinType the coin type
   * @param cursor the cursor
   * @param limit the limit
   * @return all Coin objects owned by the address.
   */
  CompletableFuture<PaginatedCoins> getCoins(
      String address, String coinType, String cursor, long limit);

  /**
   * Return the total coin balance for one coin type, owned by the address owner.
   *
   * @param address the owner address
   * @param coinType the coin type
   * @return the balance for the coin type, owned by the address owner
   */
  CompletableFuture<Balance> getBalance(String address, String coinType);

  /**
   * Return contents of a checkpoint, namely a list of execution digests.
   *
   * @param seqNum the sequence number
   * @return the contents of a checkpoint
   */
  CompletableFuture<CheckpointContents> getCheckpointContents(long seqNum);

  /**
   * Return contents of a checkpoint based on checkpoint content digest.
   *
   * @param checkpointDigest the checkpoint digest
   * @return the contents of a checkpoint
   */
  CompletableFuture<CheckpointContents> getCheckpointContentsByDigest(String checkpointDigest);

  /**
   * Return a checkpoint summary based on a checkpoint sequence number.
   *
   * @param seqNum the checkpoint sequence number
   * @return the checkpoint summary based on the checkpoint sequence number
   */
  CompletableFuture<CheckpointSummary> getCheckpointSummary(Long seqNum);

  /**
   * Return a checkpoint summary based on checkpoint digest.
   *
   * @param checkpointDigest the checkpoint digest
   * @return the checkpoint summary based on the checkpoint digest
   */
  CompletableFuture<CheckpointSummary> getCheckpointSummaryByDigest(String checkpointDigest);
}
