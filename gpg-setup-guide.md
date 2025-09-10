# GPG Setup Guide for Maven Central

## Step 1: Generate GPG Key
```bash
gpg --gen-key
```

**Follow the prompts:**
- Choose RSA (default)
- Key size: 2048 or 4096 bits
- Expiry: 0 (never expires) or 2y (2 years)
- Real name: "Mathrusoft" or your name
- Email: opensource@mathrusoft.com
- Comment: "Android Utils Library"
- Passphrase: Choose a strong password (you'll need this!)

## Step 2: Get Key ID
```bash
# List your keys to get the Key ID
gpg --list-secret-keys --keyid-format LONG
```

**Output will look like:**
```
sec   rsa2048/1234567890ABCDEF 2023-01-01 [SC]
      ABCDEF1234567890ABCDEF1234567890ABCDEF12
uid                 [ultimate] Mathrusoft <opensource@mathrusoft.com>
ssb   rsa2048/FEDCBA0987654321 2023-01-01 [E]
```

**Your KEY_ID is the last 8 characters:** `90ABCDEF`

## Step 3: Export Secret Key
```bash
# Export secret key ring
gpg --keyring secring.gpg --export-secret-keys > ~/.gnupg/secring.gpg

# On Windows, the path is usually:
# C:\Users\YourName\AppData\Roaming\gnupg\secring.gpg
```

## Step 4: Upload Public Key to Key Server
```bash
# Upload to key server (required for Maven Central)
gpg --keyserver keyserver.ubuntu.com --send-keys 1234567890ABCDEF
```

## Step 5: Your gradle.properties Values
```properties
# Replace these with your actual values:
signing.keyId=90ABCDEF
signing.password=your_gpg_passphrase_here
signing.secretKeyRingFile=C:/Users/ct024/AppData/Roaming/gnupg/secring.gpg

# Maven Central credentials
NEXUS_USERNAME=your_sonatype_username
NEXUS_PASSWORD=your_sonatype_password
```

## Troubleshooting
- If `secring.gpg` doesn't exist, create it with the export command above
- Make sure your GPG key is uploaded to a public keyserver
- The key ID should be the last 8 characters of your key fingerprint
- Keep your passphrase safe - you'll need it for signing
