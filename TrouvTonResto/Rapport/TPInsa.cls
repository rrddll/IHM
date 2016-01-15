\NeedsTeXFormat{LaTeX2e}
 
\ProvidesClass{TPInsa}[2014/11/11 Classe personnelle, V0.1]
 
% classe de base
 
\LoadClass[a4paper, 11pt]{article}
 
% extensions
 
\RequirePackage[T1]{fontenc}
\RequirePackage[utf8]{inputenc}
\RequirePackage[pdftex]{graphicx}
\RequirePackage{tocloft}
\RequirePackage{xspace}
\RequirePackage{fancyvrb}
\RequirePackage{ltablex}
\RequirePackage{placeins}
\RequirePackage[left=2.3cm, right=2.3cm, top=3cm, bottom=3cm]{geometry}

\RequirePackage{amsmath}
\RequirePackage{amsfonts}
\RequirePackage{amssymb}
\RequirePackage{lmodern}
\RequirePackage{calc}
\RequirePackage{dashrule}
\RequirePackage{booktabs}
\RequirePackage{fourier}
\RequirePackage{float}
\RequirePackage{array}
\RequirePackage{titlesec}

\RequirePackage[table]{xcolor}
\RequirePackage{fancyhdr}
\definecolor{rougeSombreRemy}{RGB}{156,0,0}
\definecolor{rougePetantRemy}{RGB}{216,0,0}
\definecolor{grisSombre}{RGB}{34, 30, 30}
\definecolor{grisClair}{RGB}{136, 130, 130}
\RequirePackage[frenchb]{babel}
\RequirePackage{subfig}
\RequirePackage{environ}
\RequirePackage[
	linktocpage=true,
	colorlinks=true,
	linkcolor=rougeSombreRemy,
	urlcolor=rougeSombreRemy
]{hyperref}
\RequirePackage{enumitem}
\RequirePackage[
	figurename=Figure , 
	tablename=Table ,
	format=plain,
	font=normal,
	labelsep=endash,
	labelfont={
		up, bf, color=rougeSombreRemy
	},
	textfont={
		normalfont
	}
				]{caption}
                
\RequirePackage{listings}
\lstdefinestyle{shared}
{
    numbers=left,
    numbersep=1em,
   	basicstyle=\ttfamily,
    breaklines=true,
    tabsize=2,
    columns=flexible,
}

\RequirePackage{tikz}

\sffamily
\RequirePackage{helvet}
\sffamily
\DeclareFontShape{T1}{lmss}{bx}{sc} { <-> ssub * phv/bx/sc }{}
\DeclareFontShape{T1}{lmss}{m}{sc} { <-> ssub * phv/m/sc }{}
\renewcommand{\rmdefault}{lmr}
\renewcommand{\sfdefault}{lmss}
\renewcommand{\ttdefault}{lmtt}
 
\newcommand{\langue}{\emph} % mots en langues étrangères
\newcommand{\cita}{\emph} % citation en italique
\newcommand{\nomprog}{\texttt} % nom de programme en police teletype
\newcommand{\octet}[1]{{\ttfamily\mbox{#1}}}
\newcommand{\spiese}{\textsc{spie sud-est}\xspace}
\newcommand{\spie}{\textsc{spie}\xspace}
\newcommand{\sap}{\textsc{sap}\xspace}
\newcommand{\abbrev}[2]{\newcommand{#1}{\textsc{#2}\xspace}}

\definecolor{grisTresClair}{RGB}{226, 220, 220}

\newcommand{\raisedrule}[2][.5ex]{\leaders\hbox{\rule[#1]{1pt}{#2}}\hfill}

\renewcommand{\familydefault}{\sfdefault}

\setlength{\parskip}{.5\baselineskip}%

\hyphenation{Forget}
\hyphenation{Luc}
\hyphenation{Quentin}
\hyphenation{Labernia}
\hyphenation{Jérémy}
\hyphenation{Wimsingues}
\hyphenation{Rémy}
\hyphenation{Leydet}
\hyphenation{Théo}
\hyphenation{Faure}
\hyphenation{Julien}
\hyphenation{Bara}
\hyphenation{Amjad}
\hyphenation{Haouriqui}

%----Custom list----------------------------------------------------
\newlist{funcdescr}{description}{1}
\setlist[funcdescr]{font={\color{black}\ttfamily}}
\setlist[itemize]{topsep=0pt,after=\vspace{.5\baselineskip}}

%----Variables------------------------------------------------------
\setcounter{secnumdepth}{4}
\setcounter{tocdepth}{4}

%sectionnement
\titleformat{\section}
{\sffamily\Large\bfseries\color{rougeSombreRemy}}{\thesection}{1em}{}

\titleformat{\subsection}
{\sffamily\large\bfseries\color{rougePetantRemy}}{\thesubsection}{1em}{}

\titleformat{\subsubsection}
{\sffamily\normalsize\bfseries}{\thesubsubsection}{1em}{}

\titleformat{\paragraph}
{\sffamily\normalsize\bfseries\color{grisClair}}{\theparagraph}{1em}{}
\titleformat{\subparagraph}[runin]
{\sffamily\normalsize\bfseries}{\thesubparagraph}{1em}{}

\titlespacing*{\chapter} {0pt}{50pt}{40pt}
\titlespacing*{\section} {0pt}{3.5ex plus 1ex minus .2ex}{2.3ex plus .2ex}
\titlespacing*{\subsection} {0pt}{3.25ex plus 1ex minus .2ex}{1.5ex plus .2ex}
\titlespacing*{\subsubsection}{0pt}{3.25ex plus 1ex minus .2ex}{1.5ex plus .2ex}
\titlespacing*{\paragraph}
{0pt}{3.25ex plus 1ex minus .2ex}{0.5ex plus .2ex}
\titlespacing*{\subparagraph} {\parindent}{3.25ex plus 1ex minus .2ex}{1em}

%-------------------------------------------------------------------

\renewcommand{\thesection}{\Roman{section}.}
\renewcommand{\thesubsection}{\Alph{subsection}.}
\renewcommand{\thesubsubsection}{\arabic{subsubsection}}
\renewcommand{\theparagraph}{\alph{paragraph}}

%-------------- Ligne grise--------------------------------------
\newcommand{\lignegrise}{\noindent{\color{gray}\hdashrule[0pt][x]{\linewidth}{2pt}{2pt}}}

%-------------- Liste des figures --------------------------------------
\renewcommand{\cftafterloftitle}{%
{\color{rougeSombreRemy}\large\sffamily\bfseries~\decofourright}\hfill\vspace{-0.4em}\strut{}\\%
\lignegrise{}}
\renewcommand{\cftloftitlefont}{\lignegrise
\par\noindent\hfill\large\color{rougeSombreRemy}\sffamily\bfseries\decofourleft~}
\renewcommand{\cftfigdotsep}{\cftnodots}
\newcommand{\listeFigures}[1][]{\clearpage#1\listoffigures%
\lignegrise\clearpage}


%-------------- Table des matières--------------------------------------
\renewcommand{\cftaftertoctitle}{%
{\color{rougeSombreRemy}\large\sffamily\bfseries~\decofourright}\hfill\vspace{-0.4em}\strut{}\\%
\lignegrise{}}
\renewcommand{\cfttoctitlefont}{\lignegrise
\par\noindent\hfill\large\color{rougeSombreRemy}\sffamily\bfseries\decofourleft~}

\addtolength{\cftsecnumwidth}{0.3em}
\setlength{\cftsubsubsecnumwidth}{1.6em}
\setlength{\cftparanumwidth}{1.6em}

\renewcommand{\cftsecfont}{\bfseries\sffamily\large\color{grisSombre}}
\renewcommand{\cftsubsecfont}{\sffamily\color{grisSombre}}
\renewcommand{\cftsecpagefont}{\bfseries\sffamily\large\color{grisSombre}}
\renewcommand{\cftsubsecpagefont}{\sffamily\color{grisSombre}}
\renewcommand{\cftsubsecdotsep}{\cftnodots}
\renewcommand{\cftsubsubsecdotsep}{\cftnodots}
\renewcommand{\cftparadotsep}{\cftnodots}



\newcommand{\tableMatieres}{\tableofcontents
\lignegrise\clearpage}

%--------------- Page titre ----------------------------------------------------
\newcommand{\pageTitre}{
\begin{titlepage}
\begin{tikzpicture}[remember picture,overlay]
	\fill[color=rougeSombreRemy] (current page.south west) rectangle
	(current page.north east);
	\fill[color=white] (current page.north west)
	++(.25\paperwidth, 0) .. controls +(-0.2\paperwidth, -0.75\paperheight) ..
	+(0.75\paperwidth, -0.85\paperheight) -- (current page.north east) --
	cycle;
	\fill[color=white] (current page.south east) ++
	(0, .05\paperheight) .. controls +(-.8\paperwidth, .1\paperheight)
	.. +(-.9\paperwidth, -.15\paperheight) -- (current page.south east);

	%Logo
	\draw[very thick, color=grisClair] (current page.south west)
		++(0.35\paperwidth, 0.5cm) -- ++(70:1.2cm) -- ++ (70:1.2cm) --
	++ (180:1.2cm) -- ++(250:0.6cm); 

	\draw[very thick, color=grisClair] (current page.south west)
		++(0.35\paperwidth, 0.5cm) ++(70:1.2cm) ++ (-0.6cm, 0) --
	++ (1.2cm, 0); 

	\draw[very thick, color=grisClair, fill=grisTresClair] (current page.south west)
	++(0.35\paperwidth, 0.5cm) ++(70:1.2cm) circle (2.5mm) ++ (-0.6cm, 0) circle(1mm)
	++ (1.2cm, 0) circle (1mm); 

	\draw[very thick, color=grisClair, fill=grisTresClair] (current page.south west)
	++(0.35\paperwidth, 0.5cm) circle(1mm) ++(70:2.4cm) circle(1mm) ++ (180:1.2cm) 
	circle (1.4mm) ++ (250:0.6cm) circle(1mm);

	\draw (current page.south west) ++ (0.40\paperwidth, 0.5cm)
	node[above right] {\sffamily \huge Heptan'hommes};

	\end{tikzpicture}
  \strut{}\vspace{2cm}\hfill\begin{minipage}[t]{0.75\linewidth}
	\flushleft
	\color{grisSombre}
	{
	\large	IHM}\vspace{8\lineskip}\par

	{\parbox{\linewidth}{\bfseries\Huge \makeatletter\@title\makeatother}\vspace{12\lineskip}}

  \strut{}\hfill{\color{rougeSombreRemy}\Large Heptanome
	4404}\vspace{24\lineskip}

	\strut{}\hfill{\LARGE\color{grisClair}\bfseries Généré le \makeatletter\@date\makeatother}
  \end{minipage}
	\vfill
	{
		\LARGE
		\strut{}\hfill\begin{minipage}[t]{\widthof{Jérémy Wimsingues}}
		Julien Bara\\
		Rémy de Leydet\\
		Théo Faure\\
		Luc Forget\\
		Amjad Haouriqui\\
		Quentin Labernia\\
		Jérémy Wimsingues
	\end{minipage}
	}
	\vfill
	\vfill

\end{titlepage}

}


%--------------------------------------------------------------Tableaux
\renewcommand{\tabularxcolumn}[1]{>{\small}m{#1}}
\renewcommand{\arraystretch}{1.2}
\newcommand{\rowstyle}[1]{\gdef\currentrowstyle{#1}#1\ignorespaces}
\newcolumntype{+}{>{\global\let\currentrowstyle\relax}}
\newcolumntype{=}{>{\currentrowstyle}}
\NewEnviron{tabularRemy}[1]{%
   \begin{tabularx}{\textwidth}{ +#1 }%
	   \rowcolor{rougePetantRemy}
	   \rowstyle{\color{white}\bfseries}
    \BODY
   \end{tabularx}
}
\rowcolors{2}{grisTresClair}{}

%---------------------------------------------------------------Images
\newcommand{\strechedFigure}[3][H]{
\begin{figure}[#1]
	\centering
	\includegraphics[width=\linewidth]{#2}
	\caption{#3}
	\end{figure}
}

\newcommand{\strechedHeightFigure}[3][H]{
\begin{figure}[#1]
	\centering
	\includegraphics[height=.90\textheight]{#2}
	\caption{#3}
	\end{figure}
}

\newcommand{\unscaledFigure}[3][H]{
\begin{figure}[#1]
	\centering
	\includegraphics{#2}
	\caption{#3}
	\end{figure}
}

\newcommand{\fixedWidthFigure}[4][H]{
\begin{figure}[#1]
	\centering
	\includegraphics[width=#4]{#2}
	\caption{#3}
	\end{figure}
}
                                 
%----------------------------------------------En têtes etc
\fancypagestyle{plain}{%
\lhead{\small\color{grisSombre}H4404}
\chead{}
\rhead{\small\color{grisSombre}\makeatletter\@title\makeatother}
\cfoot{Page \thepage}

\setlength{\headheight}{23pt}

\renewcommand{\headrulewidth}{1.6pt}% 2pt header rule
\renewcommand{\headrule}{\hbox to\headwidth{%
  \color{rougeSombreRemy}\leaders\hrule height \headrulewidth\hfill}}
  
\renewcommand{\footrulewidth}{1.6pt}
\renewcommand{\footrule}{\hbox to\headwidth{%
  \color{rougePetantRemy}\leaders\hrule height \footrulewidth\hfill}}
}

\pagestyle{plain}

\makeatletter
\renewcommand{\p@subsubsection}{\Roman{section}.\Alph{subsection}.\arabic{subsubsection}\expandafter\@gobble}
\makeatother
