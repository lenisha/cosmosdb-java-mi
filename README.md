# cosmosdb-java-mi
CosmosDB App with SQL API and ManagedIdentity Auth

## References
- https://github.com/Azure-Samples/azure-spring-boot-samples


resourceGroupName=winauth
accountName=cosmosmi
roleDefinitionId=00000000-0000-0000-0000-000000000002
principalId=6e2a077f-4416-424d-bbe3-59116dcb1de4
az cosmosdb sql role assignment create --account-name $accountName --resource-group $resourceGroupName --scope "/" --principal-id $principalId --role-definition-id $roleDefinitionId