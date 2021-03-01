Create a systemd service file for Gitea.
```shell
sudo vim /etc/systemd/system/sling.service
```

Configure the file and set User, Group and WorkDir.
```shell
[Unit]
Description=sling (sling 11 server)
After=syslog.target
After=network.target


[Service]
# LimitMEMLOCK=infinity
# LimitNOFILE=65535
RestartSec=2s
Type=simple
User=maven
# Group=maven
# WorkingDirectory=/var/lib/sling/
ExecStart=/usr/local/bin/sling
Restart=always
# Environment=USER=maven HOME=/home/maven SLING_WORK_DIR=/var/lib/sling

[Install]
WantedBy=multi-user.target
```

Reload systemd and restart Gitea service.
```shell
sudo systemctl daemon-reload
sudo systemctl enable --now sling
```
Check service status.

```shell
systemctl status sling
```
```shell
  gitea.service - Gitea (Git with a cup of tea)
   Loaded: loaded (/etc/systemd/system/gitea.service; enabled; vendor preset: enabled)
   Active: active (running) since Sun 2019-10-20 07:37:06 UTC; 27s ago
 Main PID: 2637 (gitea)
    Tasks: 9 (limit: 4719)
   Memory: 93.8M
   CGroup: /system.slice/gitea.service
           └─2637 /usr/local/bin/gitea web -c /etc/gitea/app.ini

Oct 20 07:37:06 deb10 gitea[2637]: 2019/10/20 07:37:06 routers/init.go:74:GlobalInit() [T] Custom path: /var/lib/gitea/custom
Oct 20 07:37:06 deb10 gitea[2637]: 2019/10/20 07:37:06 routers/init.go:75:GlobalInit() [T] Log path: /var/lib/gitea/log
Oct 20 07:37:06 deb10 gitea[2637]: 2019/10/20 07:37:06 ...dules/setting/log.go:226:newLogService() [I] Gitea v1.9.4 built with GNU Make 4.1, go1.12
Oct 20 07:37:06 deb10 gitea[2637]: 2019/10/20 07:37:06 ...dules/setting/log.go:269:newLogService() [I] Gitea Log Mode: Console(Console:info)
Oct 20 07:37:06 deb10 gitea[2637]: 2019/10/20 07:37:06 ...les/setting/cache.go:42:newCacheService() [I] Cache Service Enabled
Oct 20 07:37:06 deb10 gitea[2637]: 2019/10/20 07:37:06 ...s/setting/session.go:45:newSessionService() [I] Session Service Enabled
Oct 20 07:37:06 deb10 gitea[2637]: 2019/10/20 07:37:06 routers/init.go:106:GlobalInit() [I] SQLite3 Supported
Oct 20 07:37:06 deb10 gitea[2637]: 2019/10/20 07:37:06 routers/init.go:37:checkRunMode() [I] Run Mode: Development
Oct 20 07:37:06 deb10 gitea[2637]: 2019/10/20 07:37:06 cmd/web.go:151:runWeb() [I] Listen: http://0.0.0.0:3000
Oct 20 07:37:06 deb10 gitea[2637]: 2019/10/20 07:37:06 ...ce/gracehttp/http.go:142:Serve() [I] Serving [::]:3000 with pid 2637
```


Create Nginx configuration file for Gitea
```shell 
sudo vim /etc/nginx/conf.d/sling.conf
```
Paste below data into the file created.

```shell
server {
    listen 80;
    server_name sling.woodstock.fritz.box;

    location / {
        proxy_pass http://localhost:8080;
    }
}
``` 


```shell
nginx -s  reload
```