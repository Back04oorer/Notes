## Test
### Introduction
#### Structure
In this test, I created a test class for each class except the main class to perform testing. The following outlines the main content of each test class:
- `JsonParserTest` tests whether the `JsonParser` can read, write, and manage JSON files, providing usable interfaces for core functionality.
- `AdminTest` tests the unique features available to administrators.
- `UserTest` tests the features common to all users.
- `CurrencyConverterTest` tests the functionality of currency conversion.
#### Instruction
To execute all test cases, run `gradle test`. The results will be available at `build/reports/tests/test`.

To generate the code coverage test report, run `gradle jacocoTestReport`. The report can be found at `build/reports/jacoco/test`.

### Incorporating Code Coverage
In this project, we use JaCoCo to measure test coverage. The specific workflow is as follows:
- Write unit tests
- Run `gradle test` to execute the tests.
- Run `gradle jacocoTestReport` to generate the test coverage report.
- Review the generated report to ensure that most of the code logic is covered. If certain parts are not covered, write additional tests to improve the coverage.
### JUnit Test Cases
This section explains the rationale behind the design of each JUnit test, the types of tests (Normal, Edge), the inputs, and the expected outputs. All test cases that are expected to fail have been temporarily disabled in the code (using `@Disabled`) in order to generate the JaCoCo test report.
#### JsonParserTest
##### dateConverterTest1
**TestType**: Normal
**Description**: Test whether a String formatted as `dd-MM-yyyy` can be correctly converted into a `Date` Object.
**Input**: `"01-09-2024"`
**Expected Output**: The Date object corresponds to September 1, 2024
**Result**: Pass
##### dateConverterTest2
**TestType**: Edge
**Description**: Test whether a empty String can be correctly handled.
**Input**: `""`
**Expected Output**: Null
**Result**: Pass
##### dateConverterTest3
**TestType**: Edge
**Description**: Test whether a String with invalid format can be correctly handled.
**Input**: `"32-13-2024"`
**Expected Output**: Null
**Result**: Failed
##### dateConverterTest3
**TestType**: Edge
**Description**: Test whether a Null can be correctly handled.
**Input**: Null
**Expected Output**: Null
**Result**: Failed
##### readRateJsonTest1
**TestType**: Normal
**Description**: Test whether the function can correctly read a `JSON` file and return a `JSONArray`
**Input**: `src/test/resources/JsonParserTest/readRateJson_1.json`
**Expected Output**: 
```JSON
[
  {
    "origin": "USD",
    "target": "EUR",
    "rates": [
      { "date": "01-09-2024", "rate": 0.85 },
      { "date": "02-09-2024", "rate": 0.81 },
      { "date": "03-09-2024", "rate": 0.87 }
    ]
  },
  {
    "origin": "USD",
    "target": "GBP",
    "rates": [
      { "date": "01-09-2024", "rate": 0.75 },
      { "date": "02-09-2024", "rate": 0.76 },
      { "date": "03-09-2024", "rate": 0.77 }
    ]
  }
]

```
**Result**: Pass
##### getConvertJsonObjectTest1
**TestType**: Normal
**Description**: Test whether the function can correctly search and return a `JSON` object with specified currency type.
**Input**: 
```JSON
[
  {
    "origin": "USD",
    "target": "EUR",
    "rates": [
      { "date": "01-09-2024", "rate": 0.85 },
      { "date": "02-09-2024", "rate": 0.81 },
      { "date": "03-09-2024", "rate": 0.87 }
    ]
  },
  {
    "origin": "USD",
    "target": "GBP",
    "rates": [
      { "date": "01-09-2024", "rate": 0.75 },
      { "date": "02-09-2024", "rate": 0.76 }
    ]
  }
]
```

**Expected Output**:
```JSON
{
  "origin": "USD",
  "target": "EUR",
  "rates": [
    { "date": "01-09-2024", "rate": 0.85 },
    { "date": "02-09-2024", "rate": 0.81 },
    { "date": "03-09-2024", "rate": 0.87 }
  ]
}

```
**Result**: Pass
##### getConvertJsonObjectTest2
**TestType**: Edge
**Description**: Test whether the function can handle the case when the specified data does not exist in the `JSONArray`.
**Input**: 
```JSON
[
  {
    "origin": "USD",
    "target": "EUR",
    "rates": [
      { "date": "01-09-2024", "rate": 0.85 },
      { "date": "02-09-2024", "rate": 0.81 },
      { "date": "03-09-2024", "rate": 0.87 }
    ]
  },
  {
    "origin": "USD",
    "target": "GBP",
    "rates": [
      { "date": "01-09-2024", "rate": 0.75 },
      { "date": "02-09-2024", "rate": 0.76 }
    ]
  }
]
```

**Expected Output**:Null
**Result**: Pass
##### getRateTest1
**TestType**: Normal
**Description**: Test whether the function can find the correct rate with specified data and currency type.
**Input**: `"src/test/resources/JsonParserTest/getRate_1.json"`
**Expected Output**: `0.81`,`0.75`
**Result**: Pass
##### getRateTest2
**TestType**: Edge
**Description**: Test whether the function can return `0` with nonexistent data and currency type.
**Input**: `"src/test/resources/JsonParserTest/getRate_1.json"`
**Expected Output**: `0`
**Result**: Pass
##### getRateRangeTest1
**TestType**: Normal
**Description**: Test whether the function can find all the rates within a specified time period for a given currency conversion type.
**Input**: 
- `src/test/resources/JsonParserTest/getRateRange_1.json` 
- Arguments
	- `origin_in`: `USD
	- `target_in`: `EUR`
	- `start_date_in`: `31-08-2024`
	- `end_date_in`: `03-09-2024`
**Expected Output**: `[0.85, 0.82, 0.83]`
**Result**: Pass
##### getRateRangeTest2
**TestType**: Edge
**Description**: Test whether the function can correctly handle the case when there is no data within the specified time range.
**Input**: 
- `src/test/resources/JsonParserTest/getRateRange_1.json`
- Arguments
	- `origin_in`: `USD
	- `target_in`: `EUR`
	- `start_date_in`: `09-09-2024`
	- `end_date_in`: `10-09-2024`
**Expected Output**: `[]`
##### getRateRangeTest3
**TestType**: Edge
**Description**: Test whether the function can correctly return results when the time range is within the same day.
**Input**: 
- `src/test/resources/JsonParserTest/getRateRange_1.json` 
- Arguments
	- `origin_in`: `USD
	- `target_in`: `GBP`
	- `start_date_in`: `01-09-2024`
	- `end_date_in`: `01-09-2024
**Expected Output**: `[0.75]`
**Result**: Pass
##### writeToFileRateTest1
**TestType**: Normal
**Description**: Test whether the function successfully adds a record to the JSON file when the existing data does not already exist in the file.
**Input**: 
- `src/test/resources/JsonParserTest/writeToFileRate_src.json`
- Arguments
	- `origin_in`: `USD
	- `target_in`: `EUR`
	- `date_in`: `04-09-2024`
	- `rate_in`: `0.76`
**Expected Output**: A new record `{"date":"04-09-2024","rate":0.76}` should be added into `USD-EUR` currency type.
**Result**: Pass
##### writeToFileRateTest2
**TestType**: Normal
**Description**: Test whether the function updates the JSON file with new data when existing data is already present in the file.
**Input**: 
- `src/test/resources/JsonParserTest/writeToFileRate_src.json`
- Arguments
	- `origin_in`: `USD
	- `target_in`: `EUR`
	- `date_in`: `03-09-2024`
	- `rate_in`: `0.76`
**Expected Output**: 
Expected `src/test/resources/JsonParserTest/writeToFileRate_2.json`:
```JSON
[{"origin":"USD","rates":[{"date":"01-09-2024","rate":0.86},{"date":"02-09-2024","rate":0.81},{"date":"03-09-2024","rate":0.76}],"target":"EUR"},{"origin":"USD","rates":[{"date":"01-09-2024","rate":0.75},{"date":"02-09-2024","rate":0.76},{"date":"03-09-2024","rate":0.77}],"target":"GBP"}]
```
**Result**: Pass
##### writeToFileRateTest3
**TestType**:Normal
**Description**: Test whether the function should add a new currency pair and write the data when the specified currency pair does not exist in the JSON file.
**Input**: 
- `src/test/resources/JsonParserTest/writeToFileRate_src.json`
- Arguments
	- `origin_in`: `USD
	- `target_in`: `JPY`
	- `date_in`: `03-09-2024`
	- `rate_in`: `0.76`
**Expected Output**: 
Expected `src/test/resources/JsonParserTest/writeToFileRate_2.json`:
```JSON
[{"origin":"USD","rates":[{"date":"01-09-2024","rate":0.86},{"date":"02-09-2024","rate":0.81},{"date":"03-09-2024","rate":0.87}],"target":"EUR"},{"origin":"USD","rates":[{"date":"01-09-2024","rate":0.75},{"date":"02-09-2024","rate":0.76},{"date":"03-09-2024","rate":0.77}],"target":"GBP"},{"origin":"USD","rates":[{"date":"03-09-2024","rate":0.76}],"target":"JPY"}]
```
**Result**: Pass
##### writeToFileRateTest4
**TestType**:Edge
**Description**: Test whether the function can correctly handle the case when the JSON file does not exist.
**Input**: 
- `invalid_path/<>?/config2.json`
- Arguments
	- `origin_in`: `USD
	- `target_in`: `EUR`
	- `date_in`: `01-09-2024`
	- `rate_in`: `0.85`
**Expected Output**: An exception
**Result**: Pass
##### readPopularJsonTest1
**TestType**:Normal
**Description**: Test whether the function can correctly read the most 4 popular currency pairs.
**Input**: 
- `src/test/resources/JsonParserTest/readPopularJson_1.json`
**Expected Output**: `HashSet: ["AUD","CNY","EUR","USD"]`
**Result**: Pass
##### readPopularJsonTest2
**TestType**:Edge
**Description**: Test whether the function can successfully read the JSON file when there are fewer than 4 currencies stored.
**Input**: 
- `src/test/resources/JsonParserTest/readPopularJson_2.json`
**Expected Output**: Null
**Result**: Pass
##### getPreviousRateTest1
**TestType**:Normal
**Description**: Test whether the function can find the most recent rate for the specified currency pair.
**Input**: 
- `src/test/resources/JsonParserTest/getPreviousRate.json`
- Arguments
	- `origin_in`: `USD
	- `target_in`: `AUD`
**Expected Output**: 0.5
**Result**: Pass

##### getPreviousRateTest1
**TestType**:Edge
**Description**: Test whether the function can handle the case of nonexistent exchange rate data.
**Input**: 
- `src/test/resources/JsonParserTest/getPreviousRate.json`
- Arguments
	- `origin_in`: `CNY`
	- `target_in`: `JPY`
**Expected Output**: 0
**Result**: Pass

##### generatePopularCurrenciesJSONTest1
**TestType**:Normal
**Description**: Test whether the function can search for and return a `JSON` object that contains the 4 most popular currencies and their corresponding exchange rate data.
**Input**: 
- `"src/test/resources/JsonParserTest/generatePopularCurrenciesJSON_1_rate.json"`
- `"src/test/resources/JsonParserTest/generatePopularCurrenciesJSON_1_pop.json"`
**Expected Output**: 
```JSON
{
  "popularCurrencies": [
    "USD",
    "EUR",
    "CNY",
    "JPY"
  ],
  "exchangeRates": {
    "USD": {
      "EUR": {
        "rate": 0.87,
        "trend": "down"
      },
      "JPY": {
        "rate": 0.0,
        "trend": "down"
      },
      "CNY": {
        "rate": 0.0,
        "trend": "down"
      }
    },
    "EUR": {
      "USD": {
        "rate": 0.0,
        "trend": "down"
      },
      "JPY": {
        "rate": 0.0,
        "trend": "down"
      },
      "CNY": {
        "rate": 0.0,
        "trend": "down"
      }
    },
    "CNY": {
      "JPY": {
        "rate": 0.77,
        "trend": "down"
      },
      "EUR": {
        "rate": 0.0,
        "trend": "down"
      },
      "USD": {
        "rate": 0.0,
        "trend": "down"
      }
    },
    "JPY": {
      "USD": {
        "rate": 0.0,
        "trend": "down"
      },
      "EUR": {
        "rate": 0.0,
        "trend": "down"
      },
      "CNY": {
        "rate": 0.0,
        "trend": "down"
      }
    }
  }
}

```
**Result**: Pass

##### readAllCurrencyTest1
**TestType**: Normal
**Description**: Test whether the function can return all possible currency types and remove duplicates.
**Input**: 
- `src/test/resources/JsonParserTest/readAllCurrency_1.json`
**Expected Output**: 
```JSON
[USD,EUR,CNY]
```
**Result**: Pass

#### AdminTest
##### addCurrencyTest1
**TestType**: Normal  
**Description**: Test whether the function can add a new currency (AUD to JPY) with a specific rate and date into the JSON file.
**Input**:
- `src/test/resources/AdminTest/addCurrency_1.json` (initial data)
- Argument:
	- `origin`: `AUD`
	- `target`: `JPY`
	- `date`: `01-01-1998`
	- `rate`: `0.1`
**Expected Output**: The newly added rate for `AUD-JPY` on `01-01-1998` should be `0.1`.
**Result**: Pass
##### addCurrencyTest2
**TestType**: Edge Case  
**Description**: Test whether the function correctly handles the case where the currency pair already exists in the JSON file.
**Input**:
- `src/test/resources/AdminTest/addCurrency_2.json` (initial data already contains USD to AUD)
- Argument:
	- `origin`: `USD`
	- `target`: `AUD`
	- `date`: `01-01-1998`
	- `rate`: `0.1
**Expected Output**: `"Currency already exists.\n"`
**Result**: Pass
##### updateExchangeRateTest1
**TestType**: Normal  
**Description**: Test whether the function can update the exchange rate for an existing currency pair on a specific date.  
**Input**:
- `src/test/resources/AdminTest/updateExchangeRate_1.json` (initial data)
- Argument:
	- `origin`: `CNY`
	- `target`: `JPY`
	- `date`: `02-01-2024`
	- `new_rate`: `0.3
**Expected Output**: The rate for `CNY-JPY` on `02-01-2024` should be updated to `0.3`.
**Result**: Pass
##### updateExchangeRateTest2
**TestType**: Edge
**Description**: Test whether the function can add a new currency pair (CNY to MBA) when it doesn't exist and assign a new exchange rate.
**Input**:
- `"src/test/resources/AdminTest/updateExchangeRate_2.json"` (initial data)
- Argument:
	- `origin`: `CNY`
	- `target`: `MBA`
	- `date`: `02-01-2024`
	- `new_rate`: `0.2
**Expected Output**: A new currency pair (`CNY-MBA`) should be added with the rate `0.2` for the date `"02-01-2024"`.
**Result**: Pass
##### viewRateHistoryTest1
**TestType**: Normal  
**Description**: Test whether the function can retrieve the rate history between `CNY-JPY` for a specific date range. 
**Input**:
- `src/test/resources/AdminTest/viewRateHistory.json`
- Argument:
	- `origin`: `CNY`
	- `target`: `JPY`
	- `start_date`: `20-01-2023`
	- `end_date`: `13-02-2024`
 **Expected Output**: 
 ```
  "Rate history for CNY to JPY:\n" +
  "All rates: [0.2, 0.6, 1.0, 1.0, 2.0, 12.0]\n" +
  "Average rate: 2.80\n" +
  "Median rate: 1.00\n" +
  "Min rate: 0.20\n" +
  "Max rate: 12.00\n" +
  "Standard Deviation: 4.15\n"
```
 **Result**: Pass
##### viewRateHistoryTest2
**TestType**: Edge 
**Description**: Test whether the function correctly handles a single day rate history request between `CNY-JPY`.  
**Input**:
- `src/test/resources/AdminTest/viewRateHistory.json`
- Argument:
	- `origin`: `CNY`
	- `target`: `JPY`
	- `start_date`: `02-01-2024`
	- `end_date`: `02-01-2024
**Expected Output**
```
"Rate history for CNY to JPY:\n" +  
                "All rates: [1.0]\n" +  
                "Average rate: 1.00\n" +  
                "Median rate: 1.00\n" +  
                "Min rate: 1.00\n" +  
                "Max rate: 1.00\n" +  
                "Standard Deviation: 0.00\n"
```
**Result**: Pass
##### viewRateHistoryTest3
**TestType**: Edge Case  
**Description**: Test whether the function handles the case where no rates are found for a given date range.  
**Input**:
- `src/test/resources/AdminTest/viewRateHistory.json`
- Argument:
	- `origin`: `CNY`
	- `target`: `JPY`
	- `start_date`: `20-01-2025`
	- `end_date`: `13-02-2026`
**Expected Output** : `No rates found during this period\n`
##### viewRateHistoryTest4
**TestType**: Edge Case
**Description**: Test whether the function handles the case where no currency pairs are found for a given date range.  
**Input**:
- `src/test/resources/AdminTest/viewRateHistory.json`
- Argument:
	- `origin`: `CCC`
	- `target`: `CCC`
	- `start_date`: `20-01-2025`
	- `end_date`: `13-02-2026`
**Expected Output** : `No rates found during this period\n`
#### CurrencyConverterTest
##### convertTest1
**TestType**: Normal  
**Description**: Test whether the function can correctly convert an amount from USD to AUD using the exchange rate from the provided JSON file.  
**Input**:
- `src/test/resources/CurrencyConverterTest/convert_1.json`
- Argument:
	- `origin`: `USD`
	- `target`: `AUD`
	- `amount`: `64.02`
**Expected Output**: `32.01`
##### convertTest2
**TestType**: Edge Case  
**Description**: Test whether the function correctly handles a case where the target currency does not exist in the `JSON` file.  
**Input**:
- `src/test/resources/CurrencyConverterTest/convert_1.json`
- Argument:
	- `origin`: `USD`
	- `target`: `NotExist`
	- `amount`: `64.02`
**Expected Output**: `-1`
##### convertTest3
**TestType**: Edge Case  
**Description**: Test whether the function correctly handles a case where both the origin and target currencies are not found in the JSON file.  
**Input**:
- `src/test/resources/CurrencyConverterTest/convert_1.json`
-  Argument:
	- `origin`: `JPY`
	- `target`: `CNY`
	- `amount`: `64.02`
**Expected Output**: `-1`
**Result**: Pass

#### UserTest
##### convertCurrencyTest1
**TestType**: Normal
**Description**: Test whether the function can correctly convert an amount from USD to EUR using the exchange rate from the provided JSON file.  
**Input**:
- `src/test/resources/UserTest/convertCurrency_1.json`
- Argument:
	- `origin`: `USD`
	- `target`: `EUR`
	- `amount`: `62.5`
**Expected Output**: `"62.50 USD = 25.00 EUR"`
**Result**: Pass
##### convertCurrencyTest2
**TestType**: Edge
**Description**: Test whether the function handles the case where the origin currency does not exist in the JSON file.
**Input**:
- `src/test/resources/UserTest/convertCurrency_1.json`
- Argument:
	- `origin`: `CCC`
	- `target`: `EUR`
	- `amount`: `62.5`
**Expected Output**: `"Invalid currency or conversion rate."`
**Result**: Pass

#### Jacoco Test Report
Since the Main contains only UI-related code and does not involve the actual functional logic, and according to Ed's post `#274`, the final generated test report **excludes** `Main`.
![Test coverage report](https://example.com/image.png)
##### Overall Coverage
- **Instruction Coverage**: 94% of the executable instructions in the code have been covered by the tests.
- **Branch Coverage**: 88% of the conditional branches have been tested.
- **Missed Lines**: There are 21 lines of code that have not been executed during testing.
- **Missed Methods**: Only 1 method was missed, out of a total of 26 methods across the tested classes.
