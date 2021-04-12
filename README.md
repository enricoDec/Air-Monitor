# Air-Monitor

## REST Endpoints
|   | Endpoint         | HTTP Method | Content-Type     | Description                    |
|---|------------------|-------------|------------------|--------------------------------|
| 1 | monitor/live     | GET         | text/html        | Live Chart of Sensor Data      |
| 2 | monitor/history  | GET         | text/html        | History Chart of Sensor Data   |
| 3 | monitor/co?last= | GET         | application/json | Get last x Sensor Data from DB |
| 4 | monitor/co/live  | GET         | plain/text       | Get last Sensor Data from ESP  |

**JSON Reply of Endpoint 3**
``` JSON
[{"coId":1151,"ppm":13.0,"timestamp":"2021-04-08T13:33:47.419+00:00","sensorId":0},
{"coId":1152,"ppm":13.0,"timestamp":"2021-04-08T13:33:58.163+00:00","sensorId":0}]
```
## Screenshots
**History Chart**
![Screenshot 1](/screenshots/Screenshot_1.png)
**Live Chart**
![Screenshot 2](/screenshots/Screenshot_2.png)
