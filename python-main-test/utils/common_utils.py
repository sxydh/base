import time
import datetime


def get_cur_time(forma):
    return time.strftime(forma, time.localtime(time.time()))


def date_to_str(date, forma):
    return date.strftime(forma)


def str_to_date(st, forma):
    """
    %Y, Year with century as a decimal number.
    %m, Month as a zero-padded decimal number.
    %d, Day of the month as a zero-padded decimal number.
    %H, Hour (24-hour clock) as a zero-padded decimal number.
    %I, Hour (12-hour clock) as a zero-padded decimal number.
    %M, Minute as a zero-padded decimal number.
    %S, Second as a zero-padded decimal number.
    %f, Microsecond as a decimal number, zero-padded on the left.
    """
    return datetime.datetime.strptime(st, forma)


def date_cal(date, delta):
    return date + datetime.timedelta(days=delta)


print(date_cal(date=str_to_date("2019-01-01", "%Y-%m-%d"), delta=5))
