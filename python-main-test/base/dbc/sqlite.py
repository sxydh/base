import sqlite3
from decimal import Decimal


def dict_factory(cursor, row):
    d = {}
    for idx, col in enumerate(cursor.description):
        d[col[0]] = row[idx]
    return d


def adapt_decimal(decima):
    return str(decima)


def convert_decimal(t):
    return Decimal(t)


sqlite3.register_adapter(Decimal, adapt_decimal)
sqlite3.register_converter("decimal", convert_decimal)
conn = sqlite3.connect('./test.db')
conn.row_factory = dict_factory
c = conn.cursor()


def select():
    sql = 'SELECT * FROM spend WHERE id > ?'
    c.execute(sql, (-10,))
    rows = c.fetchall()
    conn.close()

    print(rows)
    return rows


def insert():
    sql = 'INSERT INTO spend (ctime, utime, amount, account_id, category_id)'
    sql += "VALUES(DATETIME(CURRENT_TIMESTAMP,'localtime'), DATETIME(CURRENT_TIMESTAMP,'localtime'), :amount, :account_id, :category_id)"
    c.execute(sql, {"account_id": Decimal(-3), "amount": Decimal(150), "category_id": Decimal(-1)})
    conn.commit()
    conn.close()


select()
# insert()
