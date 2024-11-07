## Unclustered Index
We say index is “unclustered” when index entries and data rows are not ordered in the same way

#### Explain how the index would allow the query to be calculated
**templates:**
```SQL
SELECT CId 
FROM Cust_Phone 
WHERE CPhone = '0414441777' AND CPhoneFee > 500
```
- `(CPhone,CPhoneFee,CId)`: This could be used by descending the index to the first entry after `(0414441777,500,*)`. All the immediately following index entries will be taken, and the `CId` will be returned, until we reach the first entry where `CPhone` is no longer '0414441777'
- 