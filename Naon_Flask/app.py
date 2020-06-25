from flask import Flask, render_template, request, jsonify
import DBConfig

app = Flask(__name__)

@app.route('/', methods=['POST'])
def root():
    return "root"

@app.route('/doLogin', methods=['POST'])
def doLogin():
    db_class = DBConfig.Database()
    sql = """SELECT COUNT(*), No, Name, Gubun FROM User WHERE Phone='""" + request.form['id'] + """' AND PW='""" + request.form['pw'] + """' GROUP BY No;""";
    row = db_class.executeAll(sql)

    if row != () :
        resList = {'count' : row[0]['COUNT(*)'], 'no' : row[0]['No'], 'name' : row[0]['Name'], 'gubun' : row[0]['Gubun']}
    else :
        resList = {'count' : 0}

    return jsonify(resList)

@app.route('/doCusSignUp', methods=['POST'])
def doSignUp():
    db_class = DBConfig.Database()
    sql = """INSERT INTO User(mPhone, mPW, mName) VALUES('""" + request.form['id'] + """', '""" + request.form['pw'] + """', '""" + request.form['name'] + """');"""

    db_class.execute(sql)
    db_class.commit()

    resList = {'msg' : 'success'}

    return jsonify(resList)

@app.route('/getVisitedShopList', methods=['POST'])
def getVisitedShotList():
    db_class = DBConfig.Database()
    sql = """SELECT DISTINCT u.no, u.Name, u.Address, u.Category FROM pointlog AS p JOIN user AS u ON p.UserNo = """ + \
          request.form['no'] + """ AND u.no=p.WhosShop"""

    row = db_class.executeAll(sql)

    if row != ():
        resList = {"Address" : row[0]['Address'], "Category" : row[0]['Category'], "Name" : row[0]['Name'], "no" : row[0]['no']}
        return jsonify(resList)
    else:
        return jsonify({'count': 0})




if __name__ == '__main__':
    app.run()
