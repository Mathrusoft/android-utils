# GPG Backup & Restore Guide

## 📦 Creating Backup

### 1. Export GPG Keys
```bash
# Get your key ID first
gpg --list-secret-keys --keyid-format LONG

# Export private key (KEEP SECURE!)
gpg --export-secret-keys --armor YOUR_KEY_ID > gpg-private-backup.asc

# Export public key
gpg --export --armor YOUR_KEY_ID > gpg-public-backup.asc

# Export both to single file
gpg --export-secret-keys --export --armor YOUR_KEY_ID > gpg-complete-backup.asc
```

### 2. Backup Credentials
Save these securely:
- NEXUS_USERNAME
- NEXUS_PASSWORD  
- signing.keyId
- signing.password (GPG passphrase)

### 3. Document Key Info
```bash
gpg --fingerprint YOUR_KEY_ID > key-fingerprint.txt
```

## 🔄 Restoring on New Machine

### 1. Install GPG
- Windows: Download Gpg4win
- Or use Git for Windows

### 2. Import Keys
```bash
# Import private key
gpg --import gpg-private-backup.asc

# Import public key (if separate)
gpg --import gpg-public-backup.asc

# Verify import
gpg --list-secret-keys
```

### 3. Trust Imported Key
```bash
gpg --edit-key YOUR_KEY_ID
trust
5 (ultimate)
y
quit
```

### 4. Create Secret Ring File
```bash
# Export to secring.gpg
gpg --keyring secring.gpg --export-secret-keys > "%APPDATA%\gnupg\secring.gpg"
```

### 5. Update gradle.properties
```properties
NEXUS_USERNAME=your_username
NEXUS_PASSWORD=your_password
signing.keyId=YOUR_KEY_ID
signing.password=your_gpg_passphrase
signing.secretKeyRingFile=C:/Users/USERNAME/AppData/Roaming/gnupg/secring.gpg
```

### 6. Test Signing
```bash
# Test that signing works
./gradlew :androidutils:publishToMavenLocal
```

## 🛡️ Security Tips

- Store backups on encrypted drives
- Use different passwords for different services
- Keep backups updated when keys change
- Test restore process periodically
- Never store backups in public repositories

## 📱 Team Development

Multiple developers can use the same GPG key for releases:
1. Share encrypted backup with team leads
2. Each developer imports on their machine
3. All can sign releases consistently
4. Maintain shared credential management
