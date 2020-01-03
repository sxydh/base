import wx

"""
A wx.CheckBox is a widget that has two states: on and off. It is a box with a label. The label can be set to the right 
or to the left of the box. If a wx.CheckBox is checked, it is represented by a tick in a box.
"""


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):

        pnl = wx.Panel(self)

        vbox = wx.BoxSizer(wx.HORIZONTAL)

        cb = wx.CheckBox(pnl, label='Show title')
        cb.SetValue(True)
        cb.Bind(wx.EVT_CHECKBOX, self.show_or_hide_title)

        vbox.Add(cb, flag=wx.TOP | wx.LEFT, border=30)

        pnl.SetSizer(vbox)

        self.SetTitle('wx.CheckBox')
        self.Centre()

    def show_or_hide_title(self, e):

        sender = e.GetEventObject()
        isChecked = sender.GetValue()

        if isChecked:
            self.SetTitle('wx.CheckBox')
        else:
            self.SetTitle('')


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
