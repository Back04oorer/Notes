# Symmetric Key Cryptography

### Recap of Symmetric Key Encryption
- **Core Components:** Key Generation (KeyGen), Encryption (Enc), Decryption (Dec)
- **Security Basis:** The security of symmetric key encryption relies heavily on the secrecy and randomness of the key used.

## Stream Ciphers
- **Key Strategy:** Starts by stretching a short key using a pseudorandom generator to match the plaintext length.
- **One-Time Pad Principle:** Ensures that the same key is never reused, mirroring the security guarantee of the one-time pad.

## Block Ciphers: The Foundation
- **Primary Methods:** Includes One Time pad, Stream Ciphers, and Block Ciphers.
- **Functional Difference:** Unlike stream ciphers that encrypt bit-by-bit, block ciphers encrypt fixed-size blocks of plaintext using a cryptographic key.

### Understanding Block Ciphers
- **Operational Mechanics:** Mixes chunks of plaintext bits with key bits to generate ciphertext chunks of the same size, typically 64 or 128 bits.
- **Size Consideration:** The block size plays a crucial role in balancing security and efficiency, with most block ciphers using 128-bit blocks to optimize both.

### Padding in Block Ciphers
- **Purpose:** Ensures that data perfectly fits into the block size being used, by adding redundant information to complete the last block if necessary.

## Modes of Operation in Block Ciphers

### Electronic Code Book (ECB) Mode
- **Functionality:** Encrypts each block of plaintext directly into blocks of ciphertext, with no dependency on previous blocks.
- **Characteristics:**
  - Uses a single encryption key across all blocks.
  - Allows for parallel encryption due to independent block processing.
  - Deterministic nature means identical plaintext blocks yield identical ciphertext blocks, exposing potential patterns.

### Cipher Block Chaining (CBC) Mode
- **Advancement over ECB:** Addresses security concerns by incorporating an XOR operation with the previous cipher block before encrypting the current plaintext block.
- **Sequential Processing:** Each block's encryption depends on the previous block's ciphertext, introducing an initialization vector (IV) for the first block to ensure randomness.

## Popular Encryption Algorithms
- **Notable Block Ciphers:** DES (Digital Encryption Standard), TDES (Triple DES), AES (Advanced Encryption Standard), IDEA, Twofish, and Serpent, with AES and DES being particularly significant in the field.

## MAC vs Hash
- **Message Authentication Codes (MACs):** Use a secret key alongside data to produce a tag, ensuring both integrity and sender authentication.
- **Keyed Hash Function:** Known as HMAC when incorporating a secret key with a cryptographic hash function.

### Role of MACs
- **Purpose:** Used primarily for data integrity and authentication, making it impossible for attackers to forge or validate a MAC without the secret key.

[Symmetric Key Crypto, pages 1-21](https://myaidrive.com/3d8ZsAECE5tNCPm4prXrke/3-1.Symmetri.pdf)

# Public Key Cryptography (PKC) Overview

## Key Management Challenges
- **Symmetry Requirement:** Both sender and receiver must possess the same key.
- **Protection:** The key needs to be protected at all costs.
- **Distribution and Management:** How to securely manage and distribute the key.

## Key Exchange Challenge
- **Scenario:** Establishing a key through a public channel, like a Zoom call where all communication is public.

## Key Exchange Solutions
- All communications, including attempts to establish a key, are visible to potential attackers.

## Diffie-Hellman Key Exchange
- A series of slides detailing the Diffie-Hellman Key Exchange method, which won the 2015 Turing Award for making the "difficulty gap" large enough to ensure security.

## Everyday Usage
- Examples of how Diffie-Hellman Key Exchange is used in daily communications.

## Secure Communication
- **Key Exchange:** Sharing a secret key for secure communication.
- **Public Key Encryption (PKE):** Involves a public key (PK) for encryption and a secret key (Sk) for decryption.

## Public Key Encryption (PKE)
- **Public Accessibility:** The public key can be made publicly available, for example, on a personal website.
- **Decryption:** Only the holder of the secret key can decrypt messages.

## Encryption Dreams and Realities
- **Dream:** Easy key establishment and management through public channels.
- **Reality:** Challenges in ensuring the security and management of keys.

## Digital Signature and Certification Authorities
- **Digital Signature:** A method for verifying the authenticity of digital messages.
- **Certification Authorities (CA):** Entities that bind public keys to their respective owners and issue digital certificates.

## Secure Channel Creation
- Overview of creating a secure communication channel using the principles of PKC.



