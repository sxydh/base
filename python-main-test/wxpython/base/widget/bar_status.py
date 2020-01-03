import wx

"""
The wx.StatusBar widget is used to display application status information. It can be divided into several parts to show 
different kind of information. We can insert other widgets into the wx.StatusBar. It can be used as an alternative to 
dialogs, since dialogs are ofted abused and they are disliked by most users. We can create a wx.StatusBar in two ways. 
We can manually create our own wx.StatusBar and call SetStatusBar() method or we can simply call the CreateStatusBar() 
method. The latter method creates a default wx.StatusBar for us.
"""


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):
        pnl = wx.Panel(self)

        button = wx.Button(pnl, label='Button', pos=(20, 20))
        text = wx.CheckBox(pnl, label='CheckBox', pos=(20, 90))
        combo = wx.ComboBox(pnl, pos=(120, 22), choices=['Python', 'Ruby'])
        slider = wx.Slider(pnl, 5, 6, 1, 10, (120, 90), (110, -1))

        pnl.Bind(wx.EVT_ENTER_WINDOW, self.on_widget_enter)
        button.Bind(wx.EVT_ENTER_WINDOW, self.on_widget_enter)
        text.Bind(wx.EVT_ENTER_WINDOW, self.on_widget_enter)
        combo.Bind(wx.EVT_ENTER_WINDOW, self.on_widget_enter)
        slider.Bind(wx.EVT_ENTER_WINDOW, self.on_widget_enter)

        self.sb = self.CreateStatusBar()

        self.SetSize((250, 230))
        self.SetTitle('wx.Statusbar')
        self.Centre()
        self.Show(True)

    def on_widget_enter(self, e):
        name = e.GetEventObject().GetClassName()
        self.sb.SetStatusText(name + ' widget')
        e.Skip()


def main():
    ex = wx.App()
    Example(None)
    ex.MainLoop()


if __name__ == '__main__':
    main()
