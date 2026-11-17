@echo off
cd /d "%~dp0"
copy /y "..\gradlew" . >nul
copy /y "..\gradlew.bat" . >nul
xcopy /e /i /y "..\gradle\wrapper" "gradle\wrapper" >nul
if not exist "shared\src\commonMain\composeResources\files" mkdir "shared\src\commonMain\composeResources\files"
copy /y "..\app\src\main\assets\dictionary.db" "shared\src\commonMain\composeResources\files\dictionary.db"
echo done > copy_done.txt
