from http import server
import os
print("欢迎使用xytong项目构建构建工具")
print("================================================================")
need_setup_properties = True
need_setup_rsa = True
need_setup_mysql = True
while True:
    rsa_str = input("是否需要自动生成鉴权rsa文件(需要额外依赖) 是(y)/否(n)")
    if rsa_str == "y" or rsa_str == "Y":
        need_setup_rsa = True
        break
    elif rsa_str == "n" or rsa_str == "N":
        need_setup_rsa = False
        break
    else:
        print("输入有误 请重新输入")
if(need_setup_rsa):
    try:
        import rsa  # 导入模块
    except Exception as e:
        print(e)
        cmd = 'pip install rsa'
        os.system(cmd)
        print('正在安装rsa依赖')
    finally:
        pass
    try:
        import rsa  # 导入模块
    except Exception as e:
        print(e)
        print('依赖安装失败，建议关闭代理后重试')
        exit(0)
    # 先生成一对密钥，然后保存.pem格式文件，当然也可以直接使用
    (pubkey, privkey) = rsa.newkeys(2048)
    path = "src/main/resources/access"
    folder = os.path.exists(path)

    if not folder:  # 判断是否存在文件夹如果不存在则创建为文件夹
        os.makedirs(path)  # makedirs 创建文件时如果路径不存在会创建这个路径
        print("正在创建access文件夹")
    pub = pubkey.save_pkcs1('PEM')
    str1 = pub.decode("ascii")
    pubfile = open('src/main/resources/access/rsa_token.pub_temp', 'w+')
    pubfile.write(str1)
    pubfile.close()

    pri = privkey.save_pkcs1('PEM')
    str2 = pri.decode("ascii")
    prifile = open('src/main/resources/access/rsa_token_temp', 'w+')
    prifile.write(str2)
    prifile.close()
    print("创建 PKCS#1 成功")
    os.system("openssl pkcs8 -topk8 -inform PEM -in ./src/main/resources/access/rsa_token_temp -outform pem -nocrypt -out ./src/main/resources/access/rsa_token")
    os.system("openssl rsa -in ./src/main/resources/access/rsa_token_temp -pubout -out ./src/main/resources/access/rsa_token.pub")
    print("创建 PKCS#8 成功")
    os.remove('src/main/resources/access/rsa_token.pub_temp')
    os.remove('src/main/resources/access/rsa_token_temp')

while True:
    properties_str = input("是否需要自动生成application.properties文件 是(y)/否(n)")
    if properties_str == "y" or properties_str == "Y":
        need_setup_properties = True
        break
    elif properties_str == "n" or properties_str == "N":
        need_setup_properties = False
        break
    else:
        print("输入有误 请重新输入")
mysql_database_host = ''
mysql_database_port = ''
mysql_database_name = ''
mysql_user_name = ''
mysql_pwd = ''
if need_setup_properties:
    server_port = input("请输入服务端口:")
    mysql_database_host = input("请输入mysql主机:")
    mysql_database_port = input("请输入mysql主机端口:")
    mysql_database_name = input("请输入服务连接的指定数据库:")
    mysql_user_name = input("请输入mysql用户名:")
    mysql_pwd = input("请输入mysql密码:")
    str1 = str(f"server.port={server_port}\n" +
               f"spring.datasource.url=jdbc:mysql://{mysql_database_host}:{mysql_database_port}/{mysql_database_name}\n" +
               f"spring.datasource.username={mysql_user_name}\n" +
               f"spring.datasource.password={mysql_pwd}\n" +
               f"spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver\n" +
               f"spring.jackson.time-zone=GMT+8")
    file = open('src/main/resources/application.properties', 'w+')
    file.write(str1)
while True:
    mysql_str = input("是否需要自动导入模板数据库文件(需要额外依赖) 是(y)/否(n)")
    if mysql_str == "y" or mysql_str == "Y":
        need_setup_mysql = True
        break
    elif mysql_str == "n" or mysql_str == "N":
        need_setup_mysql = False
        break
    else:
        print("输入有误 请重新输入")
if need_setup_mysql:
    try:
        import pymysql  # 导入模块
    except Exception as e:
        print(e)
        cmd = 'pip install PyMySQL'
        os.system(cmd)
        print('正在安装PyMySQL依赖')
    finally:
        pass
    try:
        import pymysql  # 导入模块
    except Exception as e:
        print(e)
        print('依赖安装失败，建议关闭代理后重试')
        exit(0)
    if mysql_database_host == "":
        mysql_database_host = input("请输入mysql主机:")
    if mysql_database_port == "":
        mysql_database_port = input("请输入mysql主机端口:")
    if mysql_database_name == "":
        mysql_database_name = input("请输入服务连接的指定数据库:")
    if mysql_user_name == "":
        mysql_user_name = input("请输入mysql用户名:")
    if mysql_pwd == "":
        mysql_pwd = input("请输入mysql密码:")
    db = pymysql.connect(host=mysql_database_host,
                         port=int(mysql_database_port),
                         user=mysql_user_name,
                         password=mysql_pwd,
                         database="")
    cursor = db.cursor()
    try:
        cursor.execute(f"create database {mysql_database_name}")
    except Exception as e:
        print(e)
        print("数据库已存在")
    print("正在导入模板数据")
    os.system(f"mysql -h{mysql_database_host} -p{mysql_database_port} -u{mysql_user_name} -p{mysql_pwd} {mysql_database_name} <  src/test/java/com/xytong/sql/all.sql")
print("配置完毕")
