// Automatically Generated -- DO NOT EDIT
// it.unisannio.catman.common.client.DataStore
package it.unisannio.catman.common.client;
import java.util.Arrays;
import com.google.web.bindery.requestfactory.vm.impl.OperationData;
import com.google.web.bindery.requestfactory.vm.impl.OperationKey;
public final class DataStoreDeobfuscatorBuilder extends com.google.web.bindery.requestfactory.vm.impl.Deobfuscator.Builder {
{
withOperation(new OperationKey("t8uABkr41kWOztNSDw$3KPabUKs="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()I")
  .withMethodName("count")
  .withRequestContext("it.unisannio.catman.domain.workflow.client.EventRequest")
  .build());
withOperation(new OperationKey("wxSx7jEnu4VgSfdk79FsoofUzqY="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Ljava/util/List;")
  .withMethodName("findAll")
  .withRequestContext("it.unisannio.catman.domain.workflow.client.EventRequest")
  .build());
withOperation(new OperationKey("L7oKoX2kcXqbbM9JJHUHw7dH5Lw="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(II)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(II)Ljava/util/List;")
  .withMethodName("listAll")
  .withRequestContext("it.unisannio.catman.domain.workflow.client.EventRequest")
  .build());
withOperation(new OperationKey("eOz44WId7cxxSVsH3Q7ZtzhxFzw="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/InstanceRequest;")
  .withDomainMethodDescriptor("()V")
  .withMethodName("persist")
  .withRequestContext("it.unisannio.catman.domain.workflow.client.CustomerRequest")
  .build());
withOperation(new OperationKey("2BLMisfX_5V7YFBI0ikvcDhnrlk="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Lit/unisannio/catman/domain/workflow/Customer;")
  .withMethodName("findByName")
  .withRequestContext("it.unisannio.catman.domain.workflow.client.CustomerRequest")
  .build());
withOperation(new OperationKey("GCwoY1mpZvav0rLK0Iem_91A0bI="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()I")
  .withMethodName("count")
  .withRequestContext("it.unisannio.catman.domain.equipment.client.SupplierRequest")
  .build());
withOperation(new OperationKey("F7H040zxbIDPukNCBAdDqhXvvps="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(II)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(II)Ljava/util/List;")
  .withMethodName("listAll")
  .withRequestContext("it.unisannio.catman.domain.equipment.client.SupplierRequest")
  .build());
withRawTypeToken("cg9eEJ2hO3lfhbzsnrqa7m9MWp4=", "it.unisannio.catman.domain.equipment.client.SupplierProxy");
withRawTypeToken("PKMl$REw_DoJBDtmhK8T4vjkq4A=", "it.unisannio.catman.domain.workflow.client.CustomerProxy");
withRawTypeToken("PpLTWIzrraS9Nsz6GfgnalkzeDU=", "it.unisannio.catman.domain.workflow.client.EventProxy");
withRawTypeToken("w1Qg$YHpDaNcHrR5HZ$23y518nA=", "com.google.web.bindery.requestfactory.shared.EntityProxy");
withRawTypeToken("FXHD5YU0TiUl3uBaepdkYaowx9k=", "com.google.web.bindery.requestfactory.shared.BaseProxy");
withClientToDomainMappings("it.unisannio.catman.domain.equipment.Supplier", Arrays.asList("it.unisannio.catman.domain.equipment.client.SupplierProxy"));
withClientToDomainMappings("it.unisannio.catman.domain.workflow.Customer", Arrays.asList("it.unisannio.catman.domain.workflow.client.CustomerProxy"));
withClientToDomainMappings("it.unisannio.catman.domain.workflow.Event", Arrays.asList("it.unisannio.catman.domain.workflow.client.EventProxy"));
}}
