# Complete GPG Setup & Backup Guide

## 🎯 Goal: Create portable GPG backup for use on any machine

## Step 1: Install GPG (One-time setup)
```bash
# Option 1: Download & Install Gpg4win
# Go to: https://www.gpg4win.org/download.html

# Option 2: Chocolatey (if you have it)
choco install gnupg

# Option 3: Scoop (if you have it) 
scoop install gnupg
```

## Step 2: Create Backup Directory Structure
```powershell
# Create main backup folder
New-Item -ItemType Directory -Path "$env:USERPROFILE\gpg_details" -Force
New-Item -ItemType Directory -Path "$env:USERPROFILE\gpg_details\keys" -Force
New-Item -ItemType Directory -Path "$env:USERPROFILE\gpg_details\credentials" -Force
New-Item -ItemType Directory -Path "$env:USERPROFILE\gpg_details\scripts" -Force
New-Item -ItemType Directory -Path "$env:USERPROFILE\gpg_details\docs" -Force
```

## Step 3: Generate GPG Key
```bash
gpg --gen-key
```
**Follow prompts:**
- Real name: `Mathrusoft`
- Email: `opensource@mathrusoft.com`
- Expiry: `2y` (2 years)
- Passphrase: Choose strong password (SAVE THIS!)

## Step 4: Get Key Information
```bash
# Get your key ID
gpg --list-secret-keys --keyid-format LONG > "$env:USERPROFILE\gpg_details\docs\key-info.txt"

# Get fingerprint
gpg --fingerprint >> "$env:USERPROFILE\gpg_details\docs\key-info.txt"
```

## Step 5: Export All Key Files
```bash
# Replace YOUR_KEY_ID with actual ID from step 4
set KEY_ID=YOUR_ACTUAL_KEY_ID

# Export private key (MOST IMPORTANT)
gpg --export-secret-keys --armor %KEY_ID% > "%USERPROFILE%\gpg_details\keys\private-key.asc"

# Export public key
gpg --export --armor %KEY_ID% > "%USERPROFILE%\gpg_details\keys\public-key.asc"

# Export secret keyring (for Gradle)
gpg --keyring secring.gpg --export-secret-keys > "%USERPROFILE%\gpg_details\keys\secring.gpg"

# Export trust database
gpg --export-ownertrust > "%USERPROFILE%\gpg_details\keys\trust-db.txt"
```

## Step 6: Create Credentials Template
```powershell
# Create credentials file template
@"
# Maven Central Credentials
NEXUS_USERNAME=your_sonatype_username_here
NEXUS_PASSWORD=your_sonatype_password_here

# GPG Signing Details  
signing.keyId=$KEY_ID
signing.password=your_gpg_passphrase_here
signing.secretKeyRingFile=C:/Users/USERNAME/AppData/Roaming/gnupg/secring.gpg

# GitHub Packages (Optional)
GITHUB_USERNAME=your_github_username
GITHUB_TOKEN=your_github_token
"@ | Out-File -FilePath "$env:USERPROFILE\gpg_details\credentials\gradle.properties.template" -Encoding UTF8
```

## Step 7: Upload Public Key to Keyserver
```bash
gpg --keyserver keyserver.ubuntu.com --send-keys %KEY_ID%
gpg --keyserver keys.openpgp.org --send-keys %KEY_ID%
```

## Step 8: Create Documentation
```powershell
# Create setup documentation
@"
# GPG Key Details for Mathrusoft Android Utils

## Key Information
- Key ID: $KEY_ID  
- Name: Mathrusoft
- Email: opensource@mathrusoft.com
- Expiry: $(Get-Date -Format 'yyyy-MM-dd') + 2 years

## Files in this backup:
- keys/private-key.asc - Private key (KEEP SECURE!)
- keys/public-key.asc - Public key
- keys/secring.gpg - Secret keyring for Gradle
- keys/trust-db.txt - Trust database
- credentials/gradle.properties.template - Credentials template
- scripts/restore.ps1 - Restore script

## Security Notes:
- Private key is password protected with your GPG passphrase
- Store this entire folder securely (encrypted drive recommended)
- Never commit these files to version control

## Usage:
1. Copy entire gpg_details folder to new machine
2. Run scripts/restore.ps1
3. Update credentials/gradle.properties with actual values
4. Ready to publish from any machine!

Created: $(Get-Date)
"@ | Out-File -FilePath "$env:USERPROFILE\gpg_details\docs\README.md" -Encoding UTF8
