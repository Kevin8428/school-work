# storage

## docker storage
- filesystem:
/var/lib/docker
    /aufs
    /containers
    /image
    /volume

#### layered architecture:
- when you delete a container, `Container Layer` is deleted
- this is anything written to container

#### volumes
- `docker volume create my_volume`
VOLUME MOUNTING:
- `docker run -v  my_voluume:/var/lib/mysql`
    - will build volume if not created


### storage driver

volume driver