# trycatchautoappp
Kod do aplikacji
krótka instrukcja obsługi tego repozytorium:
na git hubie POWINNY BYĆ tylko 2 gałęzie main i testBranch
każdy u siebie tworzy dodatkową gałąź (git checkout -b local) i nazywa ją jak chce polecam local
wszytkie nowe rzeczy nad którymi pracujemy piszemy w local.
Gdy skończycie pisać cały moduł i jesteście pewni że działa
przechodzicie u sibie na branch testBranch (git checkout testBranch)
i potem robi merge to połączy to co zrobiliście z branchem testowym (git merge local).
Jeśli wystąpią jakieś konflikty proszę je rozwiązać w sposób sensowny a nie forsować swoje zmiany.
Następnie jak już wszystko naprawicie robicie git push
Main ma być nietknięty tam będziemy wrzucać tylko przed oddawaniem jak upewnimy się że cały projekt w test branchu działa
Oczywiście przed każdym rozpoczęciem pracy proponuje robić git push lub git fetch
