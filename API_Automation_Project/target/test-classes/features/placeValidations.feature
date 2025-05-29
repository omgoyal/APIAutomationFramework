Feature: Validating place APIs
@AddPlace @Regression
Scenario Outline: Verify if place has been sucessfully added using Add Pllace API.

	Given Add Place Payload with "<name>" "<launguage>" "<address>"
	When user calls "addPlaceAPI" with "post" HTTP request
	Then the API call success status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI" with "Get" HTTP request

Examples:
  |name |launguage| address|
  |AAhouase| english| word damodar nagar|
#  |bbhouse|spanish| cross centyer|

@DeletePlace
Scenario: Verify if delete place functionaity is working

	Given Delete Place Payload 
	When user calls "deletePlaceAPI" with "post" HTTP request
	Then the API call success status code 200
	And "status" in response body is "OK"
	
@DeletePlace
Scenario: Verify if update place functionaity is working

	Given Delete Place Payload 
	When user calls "deletePlaceAPI" with "post" HTTP request
	Then the API call success status code 200
	And "status" in response body is "OK"
