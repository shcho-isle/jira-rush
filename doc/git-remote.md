### [Создание удаленного git-репозитория](https://medium.com/@nutanbhogendrasharma/setup-git-repository-in-ubuntu-20-04-vps-864f784ecab1)

`cd opt`  
`sudo mkdir jirarush` - создать директорию jirarush  
`sudo chown -R admin jirarush` - разрешить пользователю admin изменять файл jirarush.git 
`cd jirarush`  
`git init` - создать git-репозиторий  

#### Готово! Теперь мы можем добавить наш удаленный репозиторий в Intellij IDEA
_Git -> Manage Remotes_ или в терминале `git remote add jr ssh://admin@89.208.106.4:22/opt/jirarush`  
В _~/.ssh/config_ мы можем задать, [соответствие ключей и сервера](https://www.cyberciti.biz/faq/force-ssh-client-to-use-given-private-key-identity-file/):
```
Host 89.208.106.4
     IdentityFile /d/tools/putty/jirarush.pem
```

Пушим на сервер:  
`git push jr`
Если у вас ветка не `master`, на сервере надо переключиться, например на `main`: `git checkout main`  

Если push ругается, [пробуем на сервере в корне проекта](https://stackoverflow.com/a/28383598/548473):
`git config --local receive.denyCurrentBranch updateInstead`

