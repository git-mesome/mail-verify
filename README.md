# Email Verification API

이 프로젝트는 사용자 인증을 위한 이메일 검증 기능을 제공하는 API입니다. 이 API는 사용자가 입력한 이메일 주소로 인증 메일을 전송하는 기능을 포함하고 있습니다.

## 엔드포인트

### POST /api/signin/email-verification

사용자가 이메일 인증 요청을 보내기 위해 사용하는 엔드포인트입니다.

#### 요청

- **Content-Type**: `application/json`
- **Request Body**: JSON 형식으로 이메일 주소를 포함하는 `EmailAuthRequest` 객체

```json
{
  "email": "user@example.com"
}
```

### tech version
* Java 17 
* Spring Framework 5.3
