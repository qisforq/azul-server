ALTER TABLE users ALTER COLUMN username SET NOT NULL;
ALTER TABLE users ALTER COLUMN created_at SET NOT NULL;
ALTER TABLE users ALTER COLUMN hashed_password SET NOT NULL;
ALTER TABLE users ALTER COLUMN salt SET NOT NULL;
