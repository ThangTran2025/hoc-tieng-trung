@echo off
chcp 65001 >nul
cd /d "%~dp0"
echo start > run_log.txt
call COPY_ASSETS.bat >> run_log.txt 2>&1
if not exist gradlew.bat (echo THIEU gradlew.bat >> run_log.txt & exit /b 1)
call gradlew.bat :desktopApp:run --no-daemon --console=plain >> run_log.txt 2>&1
echo EXIT %errorlevel% >> run_log.txt
