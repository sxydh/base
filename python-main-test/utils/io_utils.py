import sys
import os


def get_cur_path():
    return sys.argv[0]


def get_cwd():
    return os.getcwd()


def get_cur_abs_path():
    return os.path.abspath('.')


def get_cur_file_abs_path(file_name):
    return os.path.abspath(file_name)


def get_cur_dir():
    return os.path.abspath(os.curdir)


print(os.path.abspath(os.curdir))
