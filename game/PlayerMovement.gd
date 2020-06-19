extends CSGBox


# Declare member variables here. Examples:
# var a = 2
# var b = "text"


# Called when the node enters the scene tree for the first time.
func _ready():
	pass # Replace with function body.


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	if Input.is_key_pressed(KEY_A):
		position += Vector2(move_x, move_y)
	if Input.is_key_pressed(KEY_D):
		position += Vector2(move_x, move_y)
	if Input.is_key_pressed(KEY_W):
		position += Vector2(move_x, move_y)
	if Input.is_key_pressed(KEY_S):
		position += Vector2(move_x, move_y)
