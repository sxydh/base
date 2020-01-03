def layout(obj):
    obj.Layout()
    upper = obj.GetParent()
    while upper is not None:
        upper.Layout()
        upper = upper.GetParent()
