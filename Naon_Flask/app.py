from flask import Flask, render_template, request, jsonify
import DBConfig

app = Flask(__name__)

@app.route('/', methods=['POST'])
def root():
    return "root";

@app.route('/doCusLogin', methods=['POST'])
def doLogin():
    db_class = DBConfig.DBManage()
    sql = """SELECT COUNT(*), mName FROM customer WHERE mPhone='""" + request.form['id'] + """' AND mPW='""" + request.form['pw'] + """' GROUP BY mName;""";
    row = db_class.executeAll(sql)

    if row != () :
        resList = {'count' : row[0]['COUNT(*)'], 'name' : row[0]['mName']}
    else :
        resList = {'count' : 0}

    return jsonify(resList)

@app.route('/doCusSignUp', methods=['POST'])
def doSignUp():
    db_class = DBConfig.DBManage()
    sql = """INSERT INTO customer(mPhone, mPW, mName) VALUES('""" + request.form['id'] + """', '""" + request.form['pw'] + """', '""" + request.form['name'] + """');"""

    db_class.execute(sql)
    db_class.commit()

    resList = {'msg' : 'success'}

    return jsonify(resList)

@app.route('/doShopKLogin', methods=['POST'])
def doShopKLogin():
    db_class = DBConfig.DBManage()
    sql = """SELECT COUNT(*), mShopTitle FROM shop WHERE mPhone='""" + request.form['id'] + """' AND mPW='""" + request.form['pw'] + """' GROUP BY mShopTitle;""";

    row = db_class.executeAll(sql)

    if row != ():
        resList = {'count': row[0]['COUNT(*)'], 'name': row[0]['mShopTitle']}
    else:
        resList = {'count': 0}

    return jsonify(resList)

if __name__ == '__main__':
    app.run()
