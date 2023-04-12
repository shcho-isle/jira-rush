export SPRING_PROFILES_ACTIVE=prod

alias run='cd /opt/jirarush; nohup mvn spring-boot:run -Dspring.profiles.active=prod >/dev/null 2>&1 &'
alias psjava='ps ax|grep java'
alias cdj='cd /opt/jirarush'
alias cdc='cd /opt/jirarush/config'
alias cdl='cd /opt/jirarush/logs'
alias tailf='tail -f /opt/jirarush/logs/jirarush.log'
alias ngxr='service nginx reload'
tailn(){
   tail -n $1 /opt/javaops/logs/javaops.log
}
greplog() {
   cat /opt/javaops/logs/javaops.log | grep "$1"
}
greperr() {
   cat /opt/javaops/logs/javaops.log | grep "ERR#"
}
findlog() {
   cd ~; find . -name "*.log" -exec grep "$1" {} \; -print
}
