### JsonParserTest

| Number             | Description  | Input File   | Expected Output | Status       |
| ------------------ | ------------ | ------------ | --------------- | ------------ |
| dateConverterTest1 | Row 1, Col 2 | Row 1, Col 3 | Row 1, Col 4    | Row 1, Col 5 |
| Row 2, Col 1       | Row 2, Col 2 | Row 2, Col 3 | Row 2, Col 4    | Row 2, Col 5 |
| Row 3, Col 1       | Row 3, Col 2 | Row 3, Col 3 | Row 3, Col 4    | Row 3, Col 5 |
| Row 4, Col 1       | Row 4, Col 2 | Row 4, Col 3 | Row 4, Col 4    | Row 4, Col 5 |
| Row 5, Col 1       | Row 5, Col 2 | Row 5, Col 3 | Row 5, Col 4    | Row 5, Col 5 |
| Row 6, Col 1       | Row 6, Col 2 | Row 6, Col 3 | Row 6, Col 4    | Row 6, Col 5 |
| Row 7, Col 1       | Row 7, Col 2 | Row 7, Col 3 | Row 7, Col 4    | Row 7, Col 5 |
| Row 8, Col 1       | Row 8, Col 2 | Row 8, Col 3 | Row 8, Col 4    | Row 8, Col 5 |


## 1. Currency Conversion (For both admin and user)
### Positive cases

#### 1 

#### 2

### Edge cases

#### 1.Convert an amount of 0

#### 2.Convert a very large amount

#### 3. Convert 0.001 ...

#### 4. Non numeric amount

#### 5. 转换成自己

## 2. Admin Updating Exchange Rates

### Positive

#### 1. Update the exchange rate for an existing currency and ensure it is recorded correctly in the system.

#### 2. Add a new exchange rate with a new date and verify the history is saved correctly.

### Edge 
#### 1.Update an exchange rate to 0.

#### 2. Update an exchange rate to a negative value.

## 3. Admin Adding New Currencies

### Positive
#### 1.Add a new currency and set its exchange rate, ensuring the currency can be used in subsequent conversions.

### Edge

#### 1. attempt to add an already existing concurrency

#### 2. 强制转化成大写

## 4.History Query

### Positive

#### 1.average
#### 2. Max,Min

#### 3. SD

### Edge

#### 1. Invalid Date Time ???
#### 2. One Day
#### 3.Invalid date and time

#### 4. The Currency does not exist
#### 5. long time

#### 6. 趋势没变