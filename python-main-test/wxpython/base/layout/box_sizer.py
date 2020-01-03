import wx

"""
wx.BoxSizer enables us to put several widgets into a row or a column. We can put another sizer into an existing sizer. 
This way we can create very complex layouts.
    box = wx.BoxSizer(integer orient)
    box.Add(wx.Window window, integer proportion=0, integer flag = 0, integer border = 0)
The orientation can be wx.VERTICAL or wx.HORIZONTAL. Adding widgets into the wx.BoxSizer is done via the Add() method. 
In order to understand it, we need to look at its parameters.
The proportion parameter defines the ratio of how widgets change in the defined orientation. 
Let's assume we have three buttons with the proportions 0, 1, and 2. They are added into a horizontal wx.BoxSizer. 
Button with proportion 0 will not change at all. Button with proportion 2 will change twice more than the one with 
proportion 1 in the horizontal dimension.
With the flag parameter you can further configure the behaviour of the widgets within a wx.BoxSizer. 
We can control the border between the widgets. We add some space between widgets in pixels. In order to apply border 
we need to define sides, where the border will be used. We can combine them with the | operator; 
for instance wx.LEFT | wx.BOTTOM. We can choose between these flags:
    wx.LEFT
    wx.RIGHT
    wx.BOTTOM
    wx.TOP
    wx.ALL
If we use wx.EXPAND flag, our widget will use all the space that has been allotted to it. 
Lastly, we can also define the alignment of our widgets. We do it with the following flags:
    wx.ALIGN_LEFT
    wx.ALIGN_RIGHT
    wx.ALIGN_TOP
    wx.ALIGN_BOTTOM
    wx.ALIGN_CENTER_VERTICAL
    wx.ALIGN_CENTER_HORIZONTAL
    wx.ALIGN_CENTER
"""


class BoxSizerExampleFrame(wx.Frame):
    def __init__(self, parent, title):
        super(BoxSizerExampleFrame, self).__init__(parent, title=title)

        self.init_ui()
        self.Centre()

    def init_ui(self):
        panel = wx.Panel(self)

        panel.SetBackgroundColour('#4f5049')
        vbox = wx.BoxSizer(wx.VERTICAL)

        mid_pan = wx.Panel(panel)
        mid_pan.SetBackgroundColour('#ededed')

        button1 = wx.Button(parent=mid_pan, label="button1")
        button2 = wx.Button(parent=mid_pan, label="button2")
        button3 = wx.Button(parent=mid_pan, label="button3")

        button_box = wx.BoxSizer(wx.VERTICAL)
        button_box.Add(button1, wx.ID_ANY, wx.EXPAND | wx.ALL, 20)
        button_box.Add(button2, wx.ID_ANY, wx.EXPAND | wx.ALL, 20)
        button_box.Add(button3, wx.ID_ANY, wx.EXPAND | wx.ALL, 20)

        mid_pan.SetSizer(button_box)

        vbox.Add(mid_pan, wx.ID_ANY, wx.EXPAND | wx.ALL, 20)

        panel.SetSizer(vbox)


def main():
    app = wx.App()
    ex = BoxSizerExampleFrame(None, title='BoxSizer example')
    ex.Show()
    app.MainLoop()


if __name__ == '__main__':
    main()
