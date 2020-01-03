import wx

"""
A focus indicates the currently selected widget in application. The text entered from the keyboard or pasted from the 
clipboard is sent to the widget, which has the focus. There are two event types concerning focus. The wx.EVT_SET_FOCUS 
event, which is generated when a widget receives focus. The wx.EVT_KILL_FOCUS is generated, when the widget looses 
focus. The focus is changed by clicking or by a keybord key, esually Tab or Shift+Tab.
"""


class MyWindow(wx.Panel):

    def __init__(self, parent):
        super(MyWindow, self).__init__(parent)

        self.color = '#b3b3b3'

        self.Bind(wx.EVT_PAINT, self.on_paint)
        self.Bind(wx.EVT_SIZE, self.on_size)
        self.Bind(wx.EVT_SET_FOCUS, self.on_set_focus)
        self.Bind(wx.EVT_KILL_FOCUS, self.on_kill_focus)

    def on_paint(self, e):
        dc = wx.PaintDC(self)

        dc.SetPen(wx.Pen(self.color))
        x, y = self.GetSize()
        dc.DrawRectangle(0, 0, x, y)

    def on_size(self, e):
        self.Refresh()

    def on_set_focus(self, e):
        self.color = '#ff0000'
        self.Refresh()

    def on_kill_focus(self, e):
        self.color = '#b3b3b3'
        self.Refresh()


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):
        grid = wx.GridSizer(2, 2, 10, 10)
        grid.AddMany([(MyWindow(self), 0, wx.EXPAND | wx.TOP | wx.LEFT, 9),
                      (MyWindow(self), 0, wx.EXPAND | wx.TOP | wx.RIGHT, 9),
                      (MyWindow(self), 0, wx.EXPAND | wx.BOTTOM | wx.LEFT, 9),
                      (MyWindow(self), 0, wx.EXPAND | wx.BOTTOM | wx.RIGHT, 9)])

        self.SetSizer(grid)

        self.SetSize((350, 250))
        self.SetTitle('Focus event')
        self.Centre()

    def on_move(self, e):
        print(e.GetEventObject())
        x, y = e.GetPosition()
        self.st1.SetLabel(str(x))
        self.st2.SetLabel(str(y))


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
