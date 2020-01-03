import wx

"""
wx.Frame widget is one of the most important widgets in wxPython. 
It is a container widget. It means that it can contain other widgets. Actually it can contain any window that is not a 
frame or dialog. wx.Frame consists of a title bar, borders and a central container area. The title bar and borders are 
optional. They can be removed by various flags.
"""


class Example(wx.Frame):

    def __init__(self, parent, title):
        super(Example, self).__init__(parent, title=title, size=(300, 200))

        """
        If we want to center our application on the screen, wxPython has a handy method. 
        The Centre() method simply centers the window on the screen. No need to calculate the width and the height of 
        the screen. Simply call the method.
        """
        self.Move((0, 0))
        self.Maximize()


def main():
    app = wx.App()
    ex = Example(None, title='Moving')
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
