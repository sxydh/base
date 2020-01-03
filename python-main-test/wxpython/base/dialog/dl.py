import wx

"""
Dialog windows or dialogs are an indispensable part of most modern GUI applications. A dialog is defined as a 
conversation between two or more persons. In a computer application a dialog is a window which is used to "talk" to 
the application. A dialog is used to input data, modify data, change the application settings etc. Dialogs are important 
means of communication between a user and a computer program.
"""


class Example(wx.Frame):

    def __init__(self, *args, **kwargs):
        super(Example, self).__init__(*args, **kwargs)

        self.init_ui()

    def init_ui(self):
        wx.CallLater(3000, self.show_message)

        self.SetSize((300, 200))
        self.SetTitle('Message box')
        self.Centre()

    def show_message(self):
        wx.MessageBox('Download completed', 'Info',
                      wx.OK | wx.ICON_INFORMATION)


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
