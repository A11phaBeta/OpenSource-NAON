# OpenSource-NAON  
Hanyang Univ. ERICA Open source S/W Assignment Customer membership management app service for small and medium merchants 'NAON'

---

### 1️⃣ 개발 전 Flask, MySQL 설치 및 확인과정 (Pre-development process for installing Flask, MySQL)  
---
#### 1. Flask 설치 과정 (installing Flask) ( * Python 3.8 Version이 이미 설치되어 있다고 가정함, Suppose Python 3.8 Version is already installed. )
  1. 다른 Python 프로그램들과 Flask 서버 Python 프로그램과 충돌을 방지하기 위해, virtualenv를 설치합니다. (in Macbook pro 'terminal.app')  
    ```
    $ sudo pip3 install virtualenv
    ```
    
  2. pip3를 이용하여 Flask 라이브러리를 설치합니다.  
    ```
    $ sudo pip3 install flask
    ```
#### 2. MySQL 설치 과정 (installing MySQL)
  1. Terminal을 이용하여 MySQL을 설치하기 위해 Homebrew(MacOS 패키지관리자)를 설치합니다.  
    ```
    $ /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install.sh)"
    ```
  2. Brew 패키지관리자를 이용하여 MySQL 설치합니다.  
    ```
    $ brew install mysql
    ```
  3. Brew 패키지관리자를 이용하여 MySQL의 서비스를 시작합니다.  
    ```
    $ brew services start mysql
    ```
  4. root 권한(sudo)으로 MySQL을 실행합니다.  
    ```
    $ sudo mysql
    ```

---

### 2️⃣ Android Application UI / UX 디자인 ( * 실제와 다소 차이가 있을 수 있음 )
---
#### 1. 매장고객 사용 Android Application UI / UX 디자인  
  | a. intro screen | b. login screen | c. shop list | d. points log in shop |
  |----|----|----|----|
  |![cus_intro_screen](./Design%20File/고객%20-%20Intro.jpg)|![cus_login_screen](./Design%20File/고객%20-%20Login.jpg)|![cus_shop_list_screen](./Design%20File/고객%20-%20매장%20리스트.jpg)|![cus_points_log_screen](./Design%20File/고객%20-%20포인트%20적립%20및%20사용내역.jpg)|  
#### 2. 점수 사용 Android Application UI / UX 디자인  
  | a. intro screen | b. login screen | c. add point | d. new customer sign up | e. after add point |  
  |----|----|----|----|
  |![shop_intro_screen](./Design%20File/점주%20-%20Intro.jpg)|![shop_login_screen](./Design%20File/점주%20-%20Login.jpg)|![shop_add_point_screen](./Design%20File/점주%20-%20포인트%20적립.jpg)|![shop_new_customer_signup_screen](./Design%20File/점주%20-%20포인트%20적립%20-%20신규회원%20-%20Password%20Input.jpg)|![shop_after_add_point_screen](./Design%20File/점주%20-%20포인트%20적립%20후.jpg)
