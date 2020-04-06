# 3D-Rendering
3D Rendering Environment from scratch using Java

```bash
ffmpeg -r 30 -i video/%03d.png -c:v libx264 -vf fps=25 -pix_fmt yuv420p out.mp4
```
```bash
ffmpeg -framerate 30 -pattern_type glob -i 'genImage/*.png' -c:v libx264 -pix_fmt yuv420p out2.mp4
```
```Bash
ffmpeg -framerate 30 -pattern_type glob -i "*.png" -c:v libx264 -pix_fmt yuv420p out.mp4
```


## To Do
 - [] Fix big with `error.log` intervals out of 300 causing weird behavior and plotting outside of planar bounds
 - [] Render further objects first to not overlay closer objects
    - Do this by calculating midpoint of triangles and closer to camera plane is drawn later