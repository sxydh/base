import wx


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):

        self.Bind(wx.EVT_CLOSE, self.on_close_window)

        self.SetTitle('Event veto')
        self.Centre()

    def on_close_window(self, e):

        dial = wx.MessageDialog(None, 'Are you sure to quit?', 'Question',
                                wx.YES_NO | wx.NO_DEFAULT | wx.ICON_QUESTION)

        ret = dial.ShowModal()

        if ret == wx.ID_YES:
            """
            Notice that to close the window, we must call the Destroy() method. By calling the Close() method, we would 
            end up in an endless cycle.
            """
            self.Destroy()
        else:
            """
            Sometimes we need to stop processing an event. To do this, we call the method Veto().
            """
            e.Veto()


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
