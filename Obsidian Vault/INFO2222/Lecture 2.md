### Kerckhoffs principle

- All security relies _not_ on the algorithm,but on the `_security of the key_`_._
- All algorithms should be public knowledge.

### Hash Function

- In cybersecurity, a _hash function_ is a mathematical algorithm .
    - Input (or "message")
    - Output: a _fixed-size string_ of characters, which is typically a _hexadecimal number_. _Often called a_ `_hash value_` _or_ `_hash code_`_, is unique to the input data._
- Applications
    
    data integrity verification, password storage, digital signatures…
    
    - Application I
        
        `Scenario`: When later retrieve, how do you know what you download is still what you uploaded?
        
        - Use hash to generate a short `“digest”` of the files and `store` it
        - Send the document to someone
        - Verify Integrity : Upon receiving the document, your friend also uses the `same hash function` to process the received document's content, generating a `new digest`, He then `compares` this `new digest` with the `original digest` you provided. If both digests match, it indicates that the document has not been altered during transmission.
    - Application II
        
        _Password Login_
        
        ![[Untitled.jpeg]]
        
- Properties
    - Property I: Second pre-image
        
        An expected property of a cryptographic hash function whereby it is `computationally infeasible` to find a second pre-image of a  
        known message digest.  
        
        A second pre-image attack is when an attacker is given a specific input, and can then find another input that results in the same hash.
        
        If a second pre image attack is practical against the cryptographic hash functions that we deploy, it would have significant implications  
        for our security.  
        
    - Property II: One-wayness
        
        Encryption is a `two-way function`;
        
        Hashing is a `one-way function`.
        
        While it's technically possible to reverse-hash something, the computing  
        power required makes it unfeasible.  
        
        _Hashing is one-way._
        
    - Property III: Collision resistance
        
        Need to property of collision resistance:
        
        Attacker cannot find two files/inputs x, y, such that h(x)=h(y)
        
    - Collision resistance vs Second pre-image
        
        A `collision` is, given a hash function, come up with 2 documents with the  
          
        `same hash`, but you can control both documents.
        
        It should be difficult to find two different messages m1 and m2 such that  
          
        `hash(m1)=hash(m2)` Such a pair is called a `cryptographic hash collision.`
        
        哈希函数在处理不同输入时可能会产生相同输出的潜在风险
        
          
        
        A `preimage attack is`, given `a hash function` and `one document (or one hash)`, find another document with the same hash.
        
        Given an input `m1` it should be difficult to find another input `m2` such that `m1 ≠ m2 and hash(m1)=hash(m2)` . Functions that lack this property are vulnerable to  
        second-preimage attacks.  
        
        从一个特定的哈希值反推回至少一个可能的原始输入
        
          
        

Not all existing hash functions are secure:

`MD5`, `SHA1` are both “broken” for `collision resistance`

In cryptography, if the security property cannot be guaranteed,  
we consider it broken,  
`not need to wait` for total break.

### Confidentiality for an attacker(攻击者视角下的保密)

### Attacher’s goals

- Recover the whole plaintext
- Recover some part of the plaintext
- Recover one particular part of plaintext
- Recover the key
- Recover some part of the key
- Recover one particular part of key

### Attacker’s Capability

- Sees only a target cipher-text
- Sees also some previous cipher-text
- Sees some message-ciphertext pair
- Sees message-ciphertext pair as he wishes
- Run some algorithms forever
- Run only “efficient" algorithms

  

### Perfect Secrecy for an Attacker

"Perfect Secrecy" refers to a concept in cryptography where the encryption of a message is done in such a way that an attacker, `even with unlimited resources (time, space, memory)`, cannot learn `any` information about the original message from the cipher-text, except for possibly the length of the message.

This concept is most famously encapsulated in the One-Time Pad encryption method, where if the key is `truly random`, as long as the `message itself`, and `used only once`, then the encryption is considered to have `perfect secrecy`.

### One-time Pad symmetric Encryption

随机生成的私钥仅使用一次来加密消息，然后接收者使用匹配的一次性密码本和密钥来解密该消息

### `One-time Pad Satisfies Perfect Secrecy !!!!!!!!!!`

  

A one-time pad in cryptography refers to a secure communication method where a `uniquely` `generated random key` is used `only once` for encrypting a single message. The recipient(接收者) uses a corresponding one-time pad and key for decryption. To ensure maximum security, the key is produced as a `random sequence of characters or numbers` that matches or exceeds the `length` of the message to be encrypted. This randomness is typically achieved through the use of a random number generator within a computer program, ensuring that the encryption is virtually impossible to break due to the key's uniqueness and one-time usage.

Each encryption performed with a one-time pad is `unique and independent`, eliminating the possibility of pattern detection. However, a critical challenge arises from the necessity for the `decrypting party` to possess the `identical key` used for encryption. This requirement presents significant logistical issues regarding the `secure transmission` of the key to the decrypting party and the safeguarding of both keys against unauthorized access or compromise.

![[/Untitled 32.png|Untitled 32.png]]

### Computational Security

- Runs in a limited amount of time
- Can break the system with a tiny tiny chance

### Stream Cipher & Block Cipher

A stream cipher encrypts text by applying a cryptographic key and algorithm to each bit of a data stream `individually`.

The advantage of stream ciphers lies in their ability to synchronize closely with real-time or sequential data transmissions, such as video calls or live communication. It allows for the `immediate` start of encryption as data arrives, `without waiting to compile` it into larger blocks.

  

Its main alternative, the block cipher, applies a key and algorithm to `larger blocks` of data instead of individual bits.

If a data block is incomplete, `specific padding methods` are typically used to fill the block to the `required size`. Block ciphers are suited for scenarios where the `entire dataset is available beforehand`, such as file encryption or data storage, because they necessitate organising data into complete blocks before encryption.

  

![[/Untitled 1 2.png|Untitled 1 2.png]]

  

![[/Untitled 2 2.png|Untitled 2 2.png]]

  

### Stream cipher vs one time pad

Stream cipher easy to implement, required `less key material` , `less storage space`, `bit by bit`;  
however, depends on secrecy of key, nouce, and  
pseudorandom generator.  

One time Pad theoretically `unbreakable` as every  
time a new random key-stream; however, long  
keys, more storage, very strong key distribution.  

  

### Examples of Stream Cipher

- `Salsa20`
- `ChaCha20`(modified version of Salsa20, supported in `TLS 1.3`)
- `RC4` (for wireless networks)
- `A5` (for GSM cellular networks)