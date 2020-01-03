import wx

"""
wx.ToggleButton is a button that has two states: pressed and not pressed. You toggle between these two states by 
clicking on it.
"""


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):

        pnl = wx.Panel(self)

        self.col = wx.Colour(0, 0, 0)

        rtb = wx.ToggleButton(pnl, label='red', pos=(20, 25))
        gtb = wx.ToggleButton(pnl, label='green', pos=(20, 60))
        btb = wx.ToggleButton(pnl, label='blue', pos=(20, 100))

        self.cpnl = wx.Panel(pnl, pos=(150, 20), size=(110, 110))
        self.cpnl.SetBackgroundColour(self.col)

        rtb.Bind(wx.EVT_TOGGLEBUTTON, self.toggle_red)
        gtb.Bind(wx.EVT_TOGGLEBUTTON, self.toggle_green)
        btb.Bind(wx.EVT_TOGGLEBUTTON, self.toggle_blue)

        self.SetSize((350, 250))
        self.SetTitle('Toggle buttons')
        self.Centre()

    def toggle_red(self, e):

        obj = e.GetEventObject()
        isPressed = obj.GetValue()

        green = self.col.Green()
        blue = self.col.Blue()

        if isPressed:
            self.col.Set(255, green, blue)
        else:
            self.col.Set(0, green, blue)

        self.cpnl.SetBackgroundColour(self.col)
        self.cpnl.Refresh()

    def toggle_green(self, e):

        obj = e.GetEventObject()
        isPressed = obj.GetValue()

        red = self.col.Red()
        blue = self.col.Blue()

        if isPressed:
            self.col.Set(red, 255, blue)
        else:
            self.col.Set(red, 0, blue)

        self.cpnl.SetBackgroundColour(self.col)
        self.cpnl.Refresh()

    def toggle_blue(self, e):

        obj = e.GetEventObject()
        isPressed = obj.GetValue()

        red = self.col.Red()
        green = self.col.Green()

        if isPressed:
            self.col.Set(red, green, 255)
        else:
            self.col.Set(red, green, 0)

        self.cpnl.SetBackgroundColour(self.col)
        self.cpnl.Refresh()


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
