extends Control



func _on_Button_pressed():
	get_tree().change_scene("res://mainmenu/MainMenu.tscn")


func _on_MoveTest_pressed():
	get_tree().change_scene("res://game/MoveTest/main.tscn")
