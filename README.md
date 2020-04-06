# 3D-Rendering

By NC03

**Version 1.2.3**

*3D Rendering Environment from scratch using Java*

## Commands to Generate Video

Adapted from [StackOverflow](https://stackoverflow.com/questions/24961127/how-to-create-a-video-from-images-with-ffmpeg)

```bash
ffmpeg -r 30 -i video/%03d.png -c:v libx264 -vf fps=25 -pix_fmt yuv420p out.mp4

ffmpeg -framerate 30 -pattern_type glob -i 'genImage/*.png' -c:v libx264 -pix_fmt yuv420p out2.mp4

ffmpeg -framerate 30 -pattern_type glob -i "*.png" -c:v libx264 -pix_fmt yuv420p out.mp4
```

## Command to replace version

`(version[ =]+)\d\.\d\.\d`

## To Do

- [X] Fix bug with `error.log` intervals out of 300 causing weird behavior and plotting outside of planar bounds
- [X] Render further objects first to not overlay closer objects
  - Do this by calculating midpoint of triangles and closer to camera plane is drawn later
  - Due to error of intersecting triangles where 1 is in front for 1 region and the other is in front for the other region, ray tracing is too computationally intensive to work in real time, so use above method
- [ ] Research [STL File Format](https://en.wikipedia.org/wiki/STL_%28file_format%29)

