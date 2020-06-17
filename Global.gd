extends Node

#variable setup
var currentScene = null

func _ready():
	var root = get_tree().get_root()
	currentScene = root.get_child(root.get_child_count() - 1)
#	kinda feels like robotc
func goto_scene(path):
	# From Godot documentation:
	# This function will usually be called from a signal callback,
	# or some other function in the current scene.
	# Deleting the current scene at this point is
	# a bad idea, because it may still be executing code.
	# This will result in a crash or unexpected behavior.

	# The solution is to defer the load to a later time, when
	# we can be sure that no code from the current scene is running:
	
	call_deferred("_deferred_goto_scene", path)
func _deferred_goto_scene(path):
	#its now safe to remove current scene
	currentScene.free()
	#and load the new scene
	var s = ResourceLoader.load(path)
	#instantiate it
	currentScene = s.instance
	#add it to the active scene as child of root
	get_tree().get_root().add_child(currentScene)
	#there was a compatibility thing in the documentation,
	#however it doesn't seem to be needed as I dont believe I have that API.
	#if things break relating to the SceneTree.change_scene() API, however,
	#the reason is most likely here.
