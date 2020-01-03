import wx


class ScrolledWindowTest(wx.Frame):

    def __init__(self):
        wx.Frame.__init__(self, None, -1, "ScrolledWindow", size=(300, 400))

        scrollWin = wx.ScrolledWindow(self, -1)

        x = 20
        y = 20
        for i in range(50):
            stTxt = wx.StaticText(scrollWin, -1, " Text %02d  : " % (i + 1), pos=(x, y))

            w, h = stTxt.GetSize()
            wx.TextCtrl(scrollWin, -1, pos=(x + w + 5, y))

            dy = h + 10
            y += dy

        scrollWin.SetScrollbars(0, dy, 0, y / dy + 1)
        scrollWin.SetScrollRate(10, 10)


if __name__ == '__main__':

    app = wx.App(redirect=False)
    frame = ScrolledWindowTest()
    frame.Show()

    app.MainLoop()
