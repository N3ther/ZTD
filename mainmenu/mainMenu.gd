extends Control




func _on_PlayButton_pressed():
	get_tree().change_scene("res://game/Play.tscn")
func _on_OptionsButton_pressed():
	get_tree().change_scene("res://game/Options.tscn")


func _on_ExitButton_pressed():
	get_tree().quit()
