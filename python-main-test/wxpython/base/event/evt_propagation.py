import wx

"""
There are two types of events: basic events and command events. They differ in propagation. Event propagation is 
travelling of events from child widgets to parent widgets and grand parent widgets. Basic events do not propagate. 
Command events do propagate. For example wx.CloseEvent is a basic event. It does not make sense for this event to 
propagate to parent widgets.

By default, the event that is caught in a event handler stops propagating. To continue propagation, we call the Skip() 
method.
"""


class MyPanel(wx.Panel):

    def __init__(self, *args, **kw):
        super(MyPanel, self).__init__(*args, **kw)

        self.Bind(wx.EVT_BUTTON, self.on_button_clicked)

    def on_button_clicked(self, e):
        print('event reached panel class')
        e.Skip()


class MyButton(wx.Button):

    def __init__(self, *args, **kw):
        super(MyButton, self).__init__(*args, **kw)

        self.Bind(wx.EVT_BUTTON, self.on_button_clicked)

    def on_button_clicked(self, e):
        print('event reached button class')
        """
        We process the button click event in our custom button class. The Skip() method propagates the event further to 
        to panel class.
        """
        e.Skip()


class Example(wx.Frame):

    def __init__(self, *args, **kw):
        super(Example, self).__init__(*args, **kw)

        self.init_ui()

    def init_ui(self):
        mpnl = MyPanel(self)

        MyButton(mpnl, label='Ok', pos=(15, 15))

        self.Bind(wx.EVT_BUTTON, self.on_button_clicked)

        self.SetTitle('Propagate event')
        self.Centre()

    def on_button_clicked(self, e):
        print('event reached frame class')
        e.Skip()


def main():
    app = wx.App()
    ex = Example(None)
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
